package moe.soopy.ast10106.lab8b;

public class Car2 extends Car {
	public Car2(String license) {
		super(license);
	}

	public void accelerate() {
		this.speedInKm *= 2;
		System.out.printf("%s: accelerate by 2x (now %d)\n", this.license, this.speedInKm);
	}
}
