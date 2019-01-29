

class Main
{
	public static void main(String[] args) 
	{
		String filelocation = null;
		try{
			filelocation="./json/level1.json";
			Display d = new Display("Level Design", filelocation);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}