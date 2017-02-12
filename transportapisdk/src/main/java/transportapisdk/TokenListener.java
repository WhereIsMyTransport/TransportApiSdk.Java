package transportapisdk;

public abstract class TokenListener 
{
    public abstract void tokenValid(String token, String type, String expireDate);
}