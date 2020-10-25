package cn.dyaoming.web.advices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dyaoming.errors.AppBaseException;
import cn.dyaoming.errors.AppRunTimeException;
import cn.dyaoming.models.BaseRestModel;


@SuppressWarnings("rawtypes")
public abstract class BaseExceptionAdvice implements ImportSelector{

    protected Logger log = LoggerFactory.getLogger(this.getClass());



    @ExceptionHandler(value = Exception.class)
    @Order(500000)
    @ResponseBody
    public BaseRestModel handleException(Exception exception) {
        log.error("系统发生未察觉异常，异常内容：" + exception.getMessage(), exception);
        return BaseRestModel.failed();
    }



    @ExceptionHandler(value = AppBaseException.class)
    @Order(100000)
    @ResponseBody
    public BaseRestModel handleAppBaseException(AppBaseException baseException) {
        log.error("系统发生异常，通过控制层抛向前端。", baseException);
        return BaseRestModel.failed(baseException.getCode(), baseException.getMessage());
    }



    @ExceptionHandler(value = AppRunTimeException.class)
    @Order(80000)
    @ResponseBody
    public BaseRestModel handleAppRunTimeException(AppRunTimeException runTimeException) {
        log.debug("系统发生运行时异常，异常原因：" + runTimeException.getMessage(), runTimeException);
        return BaseRestModel.failed(runTimeException.getCode(), runTimeException.getMessage());
    }



    /**
     * This implementation resolves the type of annotation from generic metadata and
     * validates that (a) the annotation is in fact present on the importing
     * {@code @Configuration} class and (b) that the given annotation has an
     * {@linkplain #getAdviceModeAttributeName() advice mode attribute} of type
     * {@link AdviceMode}.
     * <p>The {@link #selectImports(AdviceMode)} method is then invoked, allowing the
     * concrete implementation to choose imports in a safe and convenient fashion.
     * @throws IllegalArgumentException if expected annotation {@code A} is not present
     * on the importing {@code @Configuration} class or if {@link #selectImports(AdviceMode)}
     * returns {@code null}
     */
    @Override
    public final String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Class<?> annType = GenericTypeResolver.resolveTypeArgument(getClass(), AdviceModeImportSelector.class);
        Assert.state(annType != null, "Unresolvable type argument for AdviceModeImportSelector");

        AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(importingClassMetadata, annType);
        if (attributes == null) {
            throw new IllegalArgumentException(String.format(
                    "@%s is not present on importing class '%s' as expected",
                    annType.getSimpleName(), importingClassMetadata.getClassName()));
        }

        AdviceMode adviceMode = attributes.getEnum(getAdviceModeAttributeName());
        String[] imports = selectImports(adviceMode);
        if (imports == null) {
            throw new IllegalArgumentException("Unknown AdviceMode: " + adviceMode);
        }
        return imports;
    }
    
    /**
     * Determine which classes should be imported based on the given {@code AdviceMode}.
     * <p>Returning {@code null} from this method indicates that the {@code AdviceMode}
     * could not be handled or was unknown and that an {@code IllegalArgumentException}
     * should be thrown.
     * @param adviceMode the value of the {@linkplain #getAdviceModeAttributeName()
     * advice mode attribute} for the annotation specified via generics.
     * @return array containing classes to import (empty array if none;
     * {@code null} if the given {@code AdviceMode} is unknown)
     */
    @Nullable
    protected abstract String[] selectImports(AdviceMode adviceMode);
}
