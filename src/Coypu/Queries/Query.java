package Coypu.Queries;
public interface Query<TReturn>
{
    void Run();
    Object ExpectedResult();
    TReturn Result();
    double Timeout ();
    double RetryInterval();
}
