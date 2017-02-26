package transportapisdk;

public class TransportApiResult<T> 
{
	public int httpStatusCode;
	public boolean isSuccess;
	public String error;
	public T data;

	public TransportApiResult()
	{
		this.isSuccess = false;
		this.error = "";
	}
}
