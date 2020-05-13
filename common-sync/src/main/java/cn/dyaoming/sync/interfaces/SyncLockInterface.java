package cn.dyaoming.sync.interfaces;

public interface SyncLockInterface {

    boolean tryLock(String key,String serial,long expire);
        
    boolean getLock(String key,String serial,long expire,long waittime);
    
    boolean releaseLock(String key,String serial);
}
