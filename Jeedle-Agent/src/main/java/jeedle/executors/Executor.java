package jeedle.executors;

public interface Executor<T> {
    public T execute(String[] args);
}
