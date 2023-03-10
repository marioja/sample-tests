package tajdt;


public aspect PreGreetings {
	
	private int Main.greetingsCount=0;

	pointcut newGreeting(Main m): execution(public Main.new(..)) && this(m);
	pointcut greete(String msg): execution(* tajdt.Main.doGreetings(String)) && args(msg);
	pointcut greetc(String msg): call(* tajdt.Main.doGreetings(String)) && args(msg);
	pointcut greeto(String msg, Main m): execution(* tajdt.Main.doGreetings(String)) && args(msg) && this(m);
	pointcut greetno(): call(* tajdt.Main.doGreetings(String));
	
	before(String msg): greete(msg) {
		Main.logger.info("About(execute) to print this message: "+msg);
	}
	
	before(String msg): greetc(msg) {
		Main.logger.info("About(call) to print this message: "+msg);
	}
	
	before(): greetno() {
		Main.logger.info("Just before.");
	}
	
	after(String msg, Main m): greeto(msg, m){
		m.greetingsCount++;
		Main.logger.info("counted: "+m.greetingsCount);
	}
}
