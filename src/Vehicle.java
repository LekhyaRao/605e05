public class Vehicle {

  String honkSound = "vehicle honk";
  int wheels       = 4;

  public void setSound (String honkSound)    {
        this.honkSound = honkSound;
  }
  public void setSoManyWheels (int wheels)    {
        this.wheels = wheels;
  }
  public int soManyWheels ()	{
	return wheels;
  }
  public void honk()	{
	System.out.println(honkSound);
  }
}

