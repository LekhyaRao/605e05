// - will aVehicle.onlyACarCanDoThis() compile? Why?
// - what will be *.honk() outputs?

public class PoliceCar extends Car {  // Car extends Vehicle

  String honkSound = "tatue tata";
  int wheels       = 4;

  public void setSound (String honkSound)    {
        this.honkSound = honkSound;
  }
  public int soManyWheels ()    {
        return wheels;
  }
  public void onlyACarPoliceCanDoThis()	{
	System.out.println("onlyACarPoliceCanDoThis");
  }
  public void honk()	{
	System.out.println(honkSound);
  }
  public static void main(String[] args )	{
  
	PoliceCar aPoliceCar = new PoliceCar();
	Car aCar = aPoliceCar;
	Vehicle aVehicle = aCar;
	// aVehicle.honk();
	// aCar.honk();
	aPoliceCar.honk();
	aPoliceCar.onlyACarPoliceCanDoThis();
	aCar.onlyACarCanDoThis();
	//aVehicle.onlyACarCanDoThis();
  }
}

