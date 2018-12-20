class Main 
{
	public static void main(String[] args) 
	{
		String filelocation = null;
		try{
			filelocation = Main.class.getClassLoader().getResources("level1.json").nextElement().toString();
			filelocation = filelocation.substring(5);
		} catch (Exception e){
			e.printStackTrace();
		}
		Display d = new Display("Level Design", filelocation);
		System.out.println(System.getProperty("user.dir")+filelocation);
	}
}