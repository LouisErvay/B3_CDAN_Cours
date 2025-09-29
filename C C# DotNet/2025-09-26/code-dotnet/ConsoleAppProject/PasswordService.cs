using PasswordGenerator;

public static class PasswordService
{
    public static string CreatePassword(int length)
    {
        var pwd = new Password(length);
        return pwd.Next();
    }
}
