package cn.dyaoming.models;

public class DataResult  extends ApiResult {

    private Object data;


    public DataResult(){
        super();
    }

    public DataResult(boolean cFlag, String cCode){
        super(cFlag,cCode);
    }

    public DataResult(boolean cFlag, String cCode, String message){
        super(cFlag,cCode,message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
