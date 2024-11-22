package moe.soopy.ast10106.lab8b;

public class Car {
	final String license;
	public int speedInKm;

	public Car(String license) {
		this.license = license;
		this.speedInKm = 50;
	}

	public void setSpeed(int speed) {
		this.speedInKm = speed;
	}

	public void printInfo() {
		System.out.printf("%s: speed=%dkm/h\n", this.license, this.speedInKm);
	}

	public void accelerate() {
		double toAccelerate = Math.random() * 10 + 10;
		this.speedInKm += toAccelerate;
		System.out.printf("%s: accelerate %.2f (now %dkm/h)\n", this.license, toAccelerate, this.speedInKm);
	}

	public void brake() {
		double toBrake = Math.random() * 10 + 10;
		this.speedInKm -= toBrake;
		System.out.printf("%s: decelerate %.2f (now %dkm/h)\n", this.license, toBrake, this.speedInKm);
	}
}
