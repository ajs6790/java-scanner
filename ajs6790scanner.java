//		Alex Sylvester		ajs6790
//		alexjsyl@louisiana.edu
//		CMPS 450G

//import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Character;
//import java.nio.CharBuffer;

public class ajs6790scanner{

	static int charClass;
	static String lexeme;
	static char nextChar;
	static int token;
	static int nextToken;
	static int data;

	static FileReader inputStream = null;
	//static CharBuffer buffer = null;
	
	static final int LETTER = 0;
	static final int DIGIT = 1;
	static final int UNKNOWN = 99;
	static final int EOF = -1;

	static final int INT_LIT = 10;
	static final int IDENT = 11;
	static final int ADD_OP = 21;
	static final int SUB_OP = 22;
	static final int MULT_OP = 23;
	static final int DIV_OP = 24;
	static final int LEFT_PAREN = 25;
	static final int RIGHT_PAREN = 26;

	public static void main(String[] args){
		try{
			inputStream = new FileReader("front.in");
			//buffer = new CharBuffer();
			getChar();
			do{
				lex();
			} while(nextToken != EOF);
		} catch (FileNotFoundException e){
			System.out.println("File open error.");
			System.out.println("Directory: "+ System.getProperty("user.dir"));
		} finally {
			if (inputStream != null) {
				try{inputStream.close();}
				catch (IOException e){
					System.out.println("File close error.");
				}
			}
		}
	}

	public static void addChar(){
		lexeme += nextChar;
	}
		
	public static void getChar(){
	try{
		data = inputStream.read();
		if(data != -1){
			nextChar = (char) data;
			if (Character.isLetter(nextChar))
				charClass = LETTER;
			else if(Character.isDigit(nextChar))
				charClass = DIGIT;
			else charClass = UNKNOWN;
		}
		else charClass = EOF;
	}
	catch (IOException e){}
	finally{}
	}


	public static void getNonBlank() {
		while (nextChar==' ') getChar();
	}

	public static int lookup(char ch){
		switch(ch) {
			case '(':
				addChar();
				nextToken = LEFT_PAREN;
				break;
			case ')':
				addChar();
				nextToken = RIGHT_PAREN;
				break;
			case '+':
				addChar();
				nextToken = ADD_OP;
				break;
			case '-':
				addChar();
				nextToken = SUB_OP;
				break;
			case '*':
				addChar();
				nextToken = MULT_OP;
				break;
			case '/':
				addChar();
				nextToken = DIV_OP;
				break;
			default:
				addChar();
				nextToken = EOF;
				break;
		}
		return nextToken;
	}


	public static int lex(){
		lexeme = "";
		getNonBlank();
		switch (charClass){
			case LETTER:
				addChar();
				getChar();
				while (charClass == LETTER || charClass == DIGIT){
					addChar();
					getChar();
				}
				nextToken = IDENT;
				break;
			case DIGIT:
				addChar();
				getChar();
				while (charClass == DIGIT){
					addChar();
					getChar();
				}
				nextToken = INT_LIT;
				break;
			case UNKNOWN:
				lookup(nextChar);
				getChar();
				break;
			case EOF:
				nextToken = EOF;
				lexeme = "EOF";
		}
		System.out.println("Next token is: " + nextToken + ", next lexeme is: " + lexeme);
		return nextToken;
	}		
}
	






























