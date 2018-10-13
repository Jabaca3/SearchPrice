import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class readingURL {
	public static void main (String [] args) throws Exception{


		String source = getURLSource("https://www.amazon.com/HP-OMEN-17-AN012DX-i7-7700HQ-Refurbished/dp/B078961XTD?ref_=Oct_DLandingS_PC_NA_NA");
		System.out.println(source);
		//String price = searchPrice(source);
		//System.out.println(price);
	}


	public static String searchPrice(String str) {

		String buildWord = "";

		for(int i=0; i<str.length(); i++) {

			// only start building a word if you hit a $ sign
			if((str.charAt(i)+"").equals("$") && Character.isDigit(str.charAt(i+1))) {

				for(int j =i; j<10; j++) {
					String letter = str.charAt(j)+"";
					System.out.print(letter);

					//only build the word if it meets these requirement
					if(letter.equals(",") || letter.equals(".") || Character.isDigit(str.charAt(j)) || (str.charAt(i)+"").equals("$") ) {
						buildWord += letter;
						System.out.print(buildWord);
					}
				}
				return buildWord;
			}
		}
		return buildWord;
	}
	
	//retrieving the source code from the url
	public static String getURLSource(String str) throws IOException {

		URL url = new URL(str);
		URLConnection connect = url.openConnection();
		connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		return toString(connect.getInputStream());
	}



	private static String toString(InputStream inputStream) throws IOException{
		String inputLine;
		StringBuilder stringBuilder = new StringBuilder();

		//reading the source code
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))){

			while ((inputLine = bufferedReader.readLine()) != null){	
				//building a string line by line
				stringBuilder.append(inputLine);
			}
			return stringBuilder.toString(); 
		}
	}
}
