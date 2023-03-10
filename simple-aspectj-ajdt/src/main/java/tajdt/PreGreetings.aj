package tajdt;

public aspect PreGreetings {
	
	private int Main.greetingsCount=0;

	pointcut greete(String msg): execution(* tajdt.Main.doGreetings(String)) && args(msg);
	pointcut greetc(String msg): call(* tajdt.Main.doGreetings(String)) && args(msg);
	pointcut greetno(): call(* tajdt.Main.doGreetings(String));
	
	before(String msg): greete(msg) {
		System.out.println("About(execute) to print this message: "+msg);
	}
	
	before(String msg): greetc(msg) {
		System.out.println("About(call) to print this message: "+msg);
	}
	
	before(): greetno() {
		System.out.println("Just before.");
	}
	
	after(String msg): greetc(msg) {
//		Main.greetingsCount++;
//		System.out.println("counted: "+MainAJDT.greetingsCount);
	}
}
