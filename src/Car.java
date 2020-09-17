
public class Car extends Vehicle {

  String honkSound = "honk";
  int wheels       = 4;

  public void setSound (String honkSound)    {
        this.honkSound = honkSound;
  }
  public int soManyWheels ()    {
        return wheels;
  }
  public void onlyACarCanDoThis()	{
	System.out.println("onlyACarCanDoThis");
  }
  public void honk()	{
	System.out.println(honkSound);
  }
  public static void main(String[] args )	{
	new Vehicle().honk();
	new Car().honk();
  }
}

