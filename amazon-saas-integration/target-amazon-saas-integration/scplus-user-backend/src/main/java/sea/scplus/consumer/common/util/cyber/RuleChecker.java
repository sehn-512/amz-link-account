
package sea.scplus.consumer.common.util.cyber; 

import java.util.*;
import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
*	�ý��� ���� ���� ������ üũ �� ��ȯ ���� �۾��ϴ� Ŭ����.
*/
public class RuleChecker {

	
	private static final Log LOGGER = LogFactory.getLog(RuleChecker.class); 
	/** ���ڿ� ���� ���ڿ� */
	static private String numeric 	= "0123456789"; 
	/** ���ĺ��� ���� ���ڿ� */
	static private String alpha 	= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 

	/**
	*	str���ڿ��� ����ִ� old_char���ڿ��� new_char���ڿ��� ��ȯ�� ���ο� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*
	*	<br>Examples:<br><br> 
	*	<pre>
	*		Replace("mesquite in your cellar", "e", "o")
	*			returns "mosquito in your collar"
	*		Replace("the war of baronets", "r", "y")
	*			returns "the way of bayonets"
	*	</pre>
	*	
	*	@param	str			��ȯ���� ���ڿ�
	*	@param	old_char	��ȯ�� ������ ���ڿ�
	*	@param	new_char	��ȯ�� ���ο� ���ڿ�
	*	@return	the String, ��ȯ���� ���ڿ�. 
	*/
	public static String Replace( String str , String old_char , String new_char ) {
		
		if( str == null || str.equals("") ) return "";
		else {
			int fromindex = 0;
			String temp = null;
			for(int i=0 ; i<str.length() ; i++) {
				fromindex = i;
				int pos = str.indexOf(old_char,fromindex);
				if( pos != -1 ) {
					temp = str.substring(0,pos) + new_char + str.substring(pos+old_char.length());
					str = temp;
					i = pos+new_char.length()-1;
				} else break;
			}	
			return str;
		}
	}

	/**
	*	str���ڿ��� ����ִ� ch������ new_char���ڿ��� ��ȯ�� ���ο� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*
	*	<br>Examples:<br><br> 
	*	<pre>
	*		Replace("mesquite in your cellar", 'e', "o")
	*			returns "mosquito in your collar"
	*		Replace("the war of baronets", 'r', "y")
	*			returns "the way of bayonets"
	*	</pre>
	*	
	*	@param	str			��ȯ���� ���ڿ�
	*	@param	ch			��ȯ�� ������ ����
	*	@param	new_char	��ȯ�� ���ο� ���ڿ�
	*	@return	the String, ��ȯ���� ���ڿ�. 
	*/
	public static String Replace( String str , char ch, String new_char ) {
		
		Character chr = new Character(ch);
		
		String old_char = chr.toString();
		
		if( str == null || str.equals("") ) return "";
		else {
			int fromindex = 0;
			String temp = null;
			for(int i=0 ; i<str.length() ; i++) {
				fromindex = i;
				int pos = str.indexOf(old_char,fromindex);
				if( pos != -1 ) {
					temp = str.substring(0,pos) + new_char + str.substring(pos+old_char.length());
					str = temp;
					i = pos+new_char.length()-1;
				} else break;
			}	
			return str;
		}
	}

	/**
	*	str���ڿ��� ����ִ� old_char���ڿ��� ch���ڷ� ��ȯ�� ���ο� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*
	*	<br>Examples:<br><br> 
	*	<pre>
	*		Replace("mesquite in your cellar", "e", 'o')
	*			returns "mosquito in your collar"
	*		Replace("the war of baronets", "r", 'y')
	*			returns "the way of bayonets"
	*	</pre>
	*	
	*	@param	str			��ȯ���� ���ڿ�
	*	@param	old_char	��ȯ�� ������ ���ڿ�
	*	@param	ch			��ȯ�� ���ο� ����
	*	@return	the String, ��ȯ���� ���ڿ�. 
	*/
	public static String Replace( String str , String old_char , char ch  ) {
		
		Character chr = new Character(ch);
		
		String new_char = chr.toString();
		
		if( str == null || str.equals("") ) return "";
		else {
			int fromindex = 0;
			String temp = null;
			for(int i=0 ; i<str.length() ; i++) {
				fromindex = i;
				int pos = str.indexOf(old_char,fromindex);
				if( pos != -1 ) {
					temp = str.substring(0,pos) + new_char + str.substring(pos+old_char.length());
					str = temp;
					i = pos+new_char.length()-1;
				} else break;
			}	
			return str;
		}
	}
	
	/**
	*	Vector sour�� attr���� key�� �ش��ϴ� ���� value���� ���� Item�� ���ο� Vector�� �߰��Ͽ� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	sour	HashTable��ü�� Item�� �ϴ� DB������ ���� Vector����
	*	@param	attr	HashTable�� Key��
	*	@param	value	���� ��
	*	@return	the Vector, ���� ���� Item�� ���� Vector.
	*/
 	public static Vector efilter(Vector sour, String attr, String value) {
  		Vector result = new Vector();
  	
 		for(int i = 0; i < sour.size(); i++) {
 			String temp = (String)(((Hashtable)sour.elementAt(i)).get(attr.toUpperCase()));
 		
 			if(temp.equals(value))
 				result.add((Hashtable)sour.elementAt(i));
 		}
 	
 		return result;
 	
	}	

	/**
	*	Vector sour�� attr���� key�� �ش��ϴ� ���� value���� �ٸ� Item�� ���ο� Vector�� �߰��Ͽ� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	sour	HashTable��ü�� Item�� �ϴ� DB������ ���� Vector����
	*	@param	attr	HashTable�� Key��
	*	@param	value	���� ��
	*	@return	the Vector, ���� �ٸ� Item�� ���� Vector.
	*/
 	public static Vector nfilter(Vector sour, String attr, String value) {
  		Vector result = new Vector();
  	
 		for(int i = 0; i < sour.size(); i++) {
 			String temp = (String)(((Hashtable)sour.elementAt(i)).get(attr.toUpperCase()));
 		
 			if(!temp.equals(value))
 				result.add((Hashtable)sour.elementAt(i));
 		}
 	
 		return result;
 	
	}	
	
	/**
	*	char �迭�� ���� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	temp	char �迭
	*	@return	the int, char �迭�� ��.
	*/
	public static int arrayNum( char[] temp ) 
	{
		int count = 0;
		try {
			while( temp[count] != '\0' ) count++;
		} catch ( NullPointerException e) {
			LOGGER.error(e);
		} catch ( ArrayIndexOutOfBoundsException e) {
			LOGGER.error(e);
		} catch ( Exception e) {
			LOGGER.error(e);
		} finally {
		}
			return count;

	}		

	/**
	*	String �迭�� ���� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	temp	String �迭
	*	@return	the int, String �迭�� ��.
	*/	
	public static int arrayNum( String[] temp ) 
	{
		int count = 0;
		try {
			while( temp[count] != null ) count++;
		} catch ( NullPointerException e) {
		} catch ( ArrayIndexOutOfBoundsException e) {
		} catch ( Exception e) {}
		return count;
	}

	/**
	*	target ���ڿ��� ���ڷ� �̷���� �������� ���θ� �Ǵ��ϴ� �޼ҵ�.
	*	
	*	@param	target	���ڿ�
	*	@return	the boolean, ���ڷ� �̷���� ���ڿ��̸�  true, �׿ܿ��� false.
	*/	
	public static boolean isNumeric( String target ) 
	{
		for ( int i = 0 ; i < target.length() ; i++ ) {
			if ( numeric.lastIndexOf(target.charAt(i)) == -1 ) return false;
		}
		return true;
	}

	/**
	*	�ϳ��� Vector�� �Ǵٸ� Vector�� �߰��� Vector�� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	aV		Vector����
	*	@param	bV		aV �� �߰��� Vector����
	*	@return	the Vector, aV�� bV�� �߰��� Vector.
	*/
	public static Vector VaddVector( Vector aV , Vector bV ) 
	{
		for ( int i = 0 ; i < bV.size() ; i++ ) {
			aV.addElement(bV.elementAt(i));
		}
		return aV;
	}

	/**
	*	���ڿ��� ���ʿ������� Ư������ ��ŭ�� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		Left("mesquite", 5)
	*			returns "mesqu"
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@param	num		���ڿ��� ����
	*	@return	the String, ���ʿ������� Ư�������� ���ڿ�.
	*/
	public static String Left( String str , int num ) {
		if(str == null) return null;
		else if( str.length() < num ) return str;
		else return str.substring(0,num);
	}
	
	/**
	*	���ڿ��� ���ʿ������� Ư������ ��ŭ�� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*	<br>������� �ʴ� �޼ҵ��� �� ����.
	*	
	*	@param	str		�������ڿ�
	*	@param	num		���ڿ��� ����
	*	@return	the String, ���ʿ������� Ư�������� ���ڿ�.
	*/
	public static String LeftB( String str , int num ) {
		if(str == null) return null;
		else
		{
			int strByte=0;
			int strLen = str.length();
			String rtnStr = "";
			
			for(int i=0;i<strLen;i++)
    		{
    			if(str.charAt(i) >= ' ' && str.charAt(i) <= '~' ) strByte++;
    			else strByte += 2;
        		if (strByte > num) break;
        		rtnStr = rtnStr+str.substring(i,i+1);
    		}
			return rtnStr;
		}
	}

	/**
	*	���ڿ��� �����ʿ������� Ư������ ��ŭ�� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		Right("mesquite", 5)
	*			returns "quite"
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@param	num		���ڿ��� ����
	*	@return	the String, �����ʿ������� Ư�������� ���ڿ�.
	*/
	public static String Right( String str , int num ) {
		if(str == null) return null;
		else if( str.length() < num ) return str;
		else return str.substring(str.length()-num);
	}

	/**
	*	���ڿ��� Ư����ġ�������� Ư������ ��ŭ�� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		Mid("mesquite", 4, 4)
	*			returns "quit"
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@param	num		���ڿ��� ����
	*	@return	the String, Ư����ġ�������� Ư�������� ���ڿ�.
	*/
	public static String Mid( String str , int start_num , int end_num ) {
		if(str == null) return null;
		else if( str.length() < end_num || str.length() < start_num ) return str;
		else return str.substring(start_num-1,start_num-1+end_num);
	}

	/**
	*	���ڿ��� Ư�����ڿ��� �����Ͽ� String�迭�� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	strSP		�������ڿ�
	*	@param	giho		Ư�����ڿ�
	*	@return	Array of the String , Ư�����ڿ��� ���е� String�迭.
	*/	
	public static String[] splitIt(String strSP, String giho){
		
		if( strSP == null || strSP.trim().equals("")) return new String[1];

		StringTokenizer st = new StringTokenizer(RuleChecker.Replace(strSP,giho," "+giho+" "),giho);
		int stCount = st.countTokens();
		String[] temp = new String [stCount];

		for(int i=0; i < stCount; i++){
			int pos = strSP.indexOf(giho);
			if( pos >= 0 ) {
				temp[i] = strSP.substring(0, pos);
				strSP = strSP.substring(pos+giho.length());
			} else {
				temp[i] = strSP;
				break;
			}
		}
		return(temp);
	}
		
	/**
	*	���ڿ��� HTML �±׸� ������ �����ֱ� ���� encode�Ͽ� ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		HtmlEncode("<b>���ڿ�</b>")
	*			returns "&lt;b&gt;���ڿ�&lt;/b&gt;"
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@return	the String, Ư����ġ�������� Ư�������� ���ڿ�.
	*/
	public static String HtmlEncode(String str) {
		if (str == null) return "";
		StringBuffer results = null;
		char[] orig = null;
		int beg = 0, len = str.length();
		for (int i = 0; i < len; ++i){
			char c = str.charAt(i);
			switch (c){
			case 0:
			case '&':
			case '<':
			case '>':
			case '"':
				if (results == null){
					orig = str.toCharArray();
					results = new StringBuffer(len+10);
				}
				if (i > beg)
				results.append(orig, beg, i-beg);
				beg = i + 1;
				switch (c){
					default: // case 0:
					continue;
					case '&':
					results.append("&amp;");
					break;
					case '<':
					results.append("&lt;");
					break;
					case '>':
					results.append("&gt;");
					break;
					case '"':
					results.append("&quot;");
					break;
				}
				break;
			}
		}
		if (results == null) return str;
		results.append(orig, beg, len-beg);
		return results.toString();

		//if(str == null) return null;
		//else return RuleChecker.Replace(RuleChecker.Replace(RuleChecker.Replace(str,"&","&amp;"),"<","&lt;"),">","&gt;");
	}

	/**
	*	���ڿ��� Ư������("...")�� ���ļ� Ư�����̷� ��ȯ�ϴ� �޼ҵ�.
	*	<br>�ѱۿ� ���ؼ��� ���̸� 2�� ó���ϸ� �ѱ��� �����°͵� �����Ѵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		CutStr("Korea�������ô��ѹα���������", 15)
	*			returns "Korea��������..."
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@param	num		���ڿ��� ����
	*	@return	the String, Ư����ġ�������� Ư�������� ���ڿ�.
	*/
	public static String CutStr(String xxx, int limit) {
		if (limit < 5 )
			return xxx;
			
		int strlen = 0;
		char c;
		StringBuffer rtnStrBuf = new StringBuffer();
		for(int j = 0; j < xxx.length(); j++)
		{
			c = xxx.charAt(j);
			if( c == '<' || c == '>' ) {
				strlen++;
			} else {
				if ( c  <  0xac00 || 0xd7a3 < c )
				{
					strlen++;
				} 
				else  
					strlen+=2;  //�ѱ��̴�..
			}
			
			rtnStrBuf.append(c);
			if (strlen > limit-3)
			{
				rtnStrBuf.append("...");
				break;
			}				
		}
		return rtnStrBuf.toString();
	}

	/**
	*	Ư�������� ���پ� �о Vector�� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	filename	������ �����θ�
	*	@return	the Vector, ������ ������.
	*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector readFile(String filename) {
		Vector vc = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
			vc = new Vector();
			while( in.ready() ) {
				vc.add(in.readLine());
			}
			in.close();
		} catch( FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch( Exception e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
		}
			return vc;

	}

	/**
	*	Vector�� ������ ���پ� Ư�����Ͽ� �����ϴ� �޼ҵ�.
	*	
	*	@param	vc			���ڿ� ������ �ִ� Vector
	*	@param	filename	������ �����θ�
	*/
	public static void writeFile(Vector vc, String filename) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
			for( int i=0 ; i< vc.size() ; i++ ) {
				out.println((String)vc.elementAt(i));
			}
			out.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	*	��¥�� ���� "YYYYMMDD"������ ���ڿ������� �Ǵ��ϴ� �޼ҵ�.
	*	
	*	@param	strVal	�˻縦 �Ϸ��� ���ڿ�
	*	@return the boolean, �ùٸ� �����̸� true, �׿ܿ��� false.
	*/
	public static boolean isDate(String strVal){
		try {
			if(strVal != null && !strVal.equals(""))
			{
				// 8�ڸ��� ������� �� 8�� �и�
				if (strVal.length() > 8) strVal = strVal.substring(0, 8);
				
				// ����� �и�
				int    TermY  = Integer.parseInt(strVal.substring(0,4));
				int    TermM  = Integer.parseInt(strVal.substring(4,6));
				int    TermD  = Integer.parseInt(strVal.substring(6,8));
				
				// ��Ȯ��
				if (TermM < 1 || TermM > 12) return false;
				
				// ��Ȯ��
				int[] day_num = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
				if(((TermY % 4 == 0) && (TermY % 100 != 0)) || (TermY % 400 == 0)) day_num[1]=29;
				
				if (TermD < 1 || TermD > day_num[TermM-1] ) return false;
			}
			else return false;
		} catch (Exception e) {
			e.printStackTrace(); return false;
		}
		return true;
	}

	/**
	*	���ڿ����� ���鹮��(' ', '\n', '\r', '\t')�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		DelBlank("�ƹ����� �濡 ���Ŵ�")
	*			returns "�ƹ������濡���Ŵ�"
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@return	the String, ���鹮�ڸ� ������ ���ڿ�.
	*/
	public static String DelBlank( String str ) {
		if( str == null || str.trim().equals("")) return str;
		int len = str.length();
		String ret = "";
		for( int i =0 ; i<len ; i++ ) {
			if( !str.substring(i,i+1).equals(" ") && !str.substring(i,i+1).equals("\t") && !str.substring(i,i+1).equals("\r") && !str.substring(i,i+1).equals("\n")) {
				ret += str.substring(i,i+1);
			}
		}
		return ret;
	}
	
	/**
	*	���ڿ����� ���� ���鹮��(' ', '\n', '\r', '\t')�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	<br>Examples:<br><br> 
	*	<pre>
	*		rtrim("������  ")
	*			returns "������"
	*	</pre>
	*	
	*	@param	str		�������ڿ�
	*	@return	the String, ���鹮�ڸ� ������ ���ڿ�.
	*/
	public static String rtrim( String str ) {
		if( str == null || str.trim().equals("")) return str;
		while(str.substring(str.length()-1,str.length()).equals(" ")
		    ||str.substring(str.length()-1,str.length()).equals("\t")
		    ||str.substring(str.length()-1,str.length()).equals("\0")
		    ||str.substring(str.length()-1,str.length()).equals("\n")
		    ||str.substring(str.length()-1,str.length()).equals("\r")) {
			str = str.substring(0,str.length()-1);
		}
		return str;
	}

	/**
	*	���ڿ����� URL�ּҸ� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	str		�������ڿ�
	*	@return	the String, URL�ּ�.
	*/
	public static String create_url( String str ) {
		String tem1 = "";
		String tem2 = "";
		String ret = str;
		
		try {
			ret = RuleChecker.Replace(ret,"/./","/");
			while( ret.indexOf("../") >= 0 ) {
				int i= ret.indexOf("../");
				tem1 = ret.substring(0,i-1);
				tem2 = ret.substring(i);
				tem1 = tem1.substring(0,tem1.lastIndexOf("/")+1);
				tem2 = tem2.substring(3);
				ret = tem1+tem2;
			}
		} catch ( Exception e ) {
			ret = str;
		} finally {
		}
			return ret;

	}

	/**
	*	Date��ü�� Ư�� ��¥������ ���ڿ��� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	formatType		��¥����
	*	@param	currentDate		��¥ ��ü
	*	@return	the String, Ư�������� ��¥ ���ڿ�.
	*/
	public static String format(String formatType, Date currentDate){
		 //�� : formatType ="yyyy MM dd HH mm ss"
		  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(formatType,java.util.Locale.KOREA);

		   String formattedDate = formatter.format(currentDate);

		   return formattedDate;

	}

	/**
	*	���ڿ� ���� ���ڿ��� �տ� Ư���� ���̿� ���缭 "0"�� ä�� ��ȯ�ϴ� �޼ҵ�.
	*	
	*	@param	str		���ڹ��ڿ�
	*	@param	len		Ư���� ����
	*	@return	the String, "0"�� �����ϴ� ���ڿ�
	*/
	public static String AddZeroLen(String str , int len) {
		if( str == null ) return str;
		if( str.length() < len ) {
			int temp = len - str.length();
			for( int i=0 ; i<temp ; i++ )
				str = "0" + str;
			return str;
		} else {
			return str;
		}
	}

	/**
	*	�Է��� String���� ���ڿ�("") �Ǵ� null ���� ��� 0�� ����.  
	*	
	*	@param	str		���ڹ��ڿ�
	*	@return	the int parseInt()�� ��
	*/
	public static int parseInt(String intStr)
	{
		try{
			int i=Integer.parseInt(intStr);
			return i;
		}catch(Exception e){
			return 0;
		}
	}

}