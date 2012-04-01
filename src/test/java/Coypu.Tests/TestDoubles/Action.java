package Coypu.Tests.TestDoubles;

public interface Action<T> {
    public void invoke(T on);
}