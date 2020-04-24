package cn.dyaoming.sync.interfaces;

public interface SyncLockInterface {

    boolean tryLock();
        
    boolean getLock(String key,String serial);
    
    boolean releaseLock(String key,String serial);
    
    
}
