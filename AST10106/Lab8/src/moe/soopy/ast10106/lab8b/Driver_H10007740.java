package moe.soopy.ast10106.lab8b;

public class Driver_H10007740 {
	public static void main(String[] args) {
		// initialize car objects
		Car car1 = new Car("AB1234");
		Car2 car2 = new Car2("HK2017");
		Car car3 = new Car("IT1082");

		car1.setSpeed(30);
		car2.setSpeed(1);
		car3.setSpeed(88);

		// acceleration
		for (int i = 0; i < 30; i++) {
			car1.accelerate();
			car2.accelerate();
			car3.accelerate();
		}
		// deceleration
		for (int i = 0; i < 30; i++) {
			car1.brake();
			car2.brake();
			car3.brake();
		}
		car1.printInfo();
		car2.printInfo();
		car3.printInfo();
	}
}
