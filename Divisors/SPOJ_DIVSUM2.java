/*
  Problem at : https://www.spoj.com/problems/DIVSUM2/
*/

import java.math.BigInteger;
import java.io.*;
import java.util.*;

public class Main {
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static Random random = new Random();
    static BigInteger[] factors = new BigInteger[64];
	static int prlen = 0;

    public static BigInteger f(BigInteger x, BigInteger c, BigInteger N) {
        return x.multiply(x).mod(N).add(c);
    }

    private static final BigInteger[] smallPrimes = {
        new BigInteger("2"), new BigInteger("3"), new BigInteger("5"), new BigInteger("7"), new BigInteger("11"), new BigInteger("13"), new BigInteger("17"), new BigInteger("19"), new BigInteger("23"), new BigInteger("29"), new BigInteger("31"), new BigInteger("37"), new BigInteger("41"), new BigInteger("43"), new BigInteger("47"), new BigInteger("53"), new BigInteger("59"), new BigInteger("61"), new BigInteger("67"), new BigInteger("71"), new BigInteger("73"), new BigInteger("79"), new BigInteger("83"), new BigInteger("89"), new BigInteger("97"), new BigInteger("101"), new BigInteger("103"), new BigInteger("107"), new BigInteger("109"), new BigInteger("113"), new BigInteger("127"), new BigInteger("131"), new BigInteger("137"), new BigInteger("139"), new BigInteger("149"), new BigInteger("151"), new BigInteger("157"), new BigInteger("163"), new BigInteger("167"), new BigInteger("173"), new BigInteger("179"), new BigInteger("181"), new BigInteger("191"), new BigInteger("193"), new BigInteger("197"), new BigInteger("199"), new BigInteger("211"), new BigInteger("223"), new BigInteger("227"), new BigInteger("229"), new BigInteger("233"), new BigInteger("239"), new BigInteger("241"), new BigInteger("251"), new BigInteger("257"), new BigInteger("263"), new BigInteger("269"), new BigInteger("271"), new BigInteger("277"), new BigInteger("281"), new BigInteger("283"), new BigInteger("293"), new BigInteger("307"), new BigInteger("311"), new BigInteger("313"), new BigInteger("317"), new BigInteger("331"), new BigInteger("337"), new BigInteger("347"), new BigInteger("349"), new BigInteger("353"), new BigInteger("359"), new BigInteger("367"), new BigInteger("373"), new BigInteger("379"), new BigInteger("383"), new BigInteger("389"), new BigInteger("397"), new BigInteger("401"), new BigInteger("409"), new BigInteger("419"), new BigInteger("421"), new BigInteger("431"), new BigInteger("433"), new BigInteger("439"), new BigInteger("443"), new BigInteger("449"), new BigInteger("457"), new BigInteger("461"), new BigInteger("463"), new BigInteger("467"), new BigInteger("479"), new BigInteger("487"), new BigInteger("491"), new BigInteger("499"), new BigInteger("503"), new BigInteger("509"), new BigInteger("521"), new BigInteger("523"), new BigInteger("541"), new BigInteger("547"), new BigInteger("557"), new BigInteger("563"), new BigInteger("569"), new BigInteger("571"), new BigInteger("577"), new BigInteger("587"), new BigInteger("593"), new BigInteger("599"), new BigInteger("601"), new BigInteger("607"), new BigInteger("613"), new BigInteger("617"), new BigInteger("619"), new BigInteger("631"), new BigInteger("641"), new BigInteger("643"), new BigInteger("647"), new BigInteger("653"), new BigInteger("659"), new BigInteger("661"), new BigInteger("673"), new BigInteger("677"), new BigInteger("683"), new BigInteger("691"), new BigInteger("701"), new BigInteger("709"), new BigInteger("719"), new BigInteger("727"), new BigInteger("733"), new BigInteger("739"), new BigInteger("743"), new BigInteger("751"), new BigInteger("757"), new BigInteger("761"), new BigInteger("769"), new BigInteger("773"), new BigInteger("787"), new BigInteger("797"), new BigInteger("809"), new BigInteger("811"), new BigInteger("821"), new BigInteger("823"), new BigInteger("827"), new BigInteger("829"), new BigInteger("839"), new BigInteger("853"), new BigInteger("857"), new BigInteger("859"), new BigInteger("863"), new BigInteger("877"), new BigInteger("881"), new BigInteger("883"), new BigInteger("887"), new BigInteger("907"), new BigInteger("911"), new BigInteger("919"), new BigInteger("929"), new BigInteger("937"), new BigInteger("941"), new BigInteger("947"), new BigInteger("953"), new BigInteger("967"), new BigInteger("971"), new BigInteger("977"), new BigInteger("983"), new BigInteger("991"), new BigInteger("997"), new BigInteger("1009"), new BigInteger("1013"), new BigInteger("1019"), new BigInteger("1021"), new BigInteger("1031"), new BigInteger("1033"), new BigInteger("1039"), new BigInteger("1049"), new BigInteger("1051"), new BigInteger("1061"), new BigInteger("1063"), new BigInteger("1069"), new BigInteger("1087"), new BigInteger("1091"), new BigInteger("1093"), new BigInteger("1097"), new BigInteger("1103"), new BigInteger("1109"), new BigInteger("1117"), new BigInteger("1123"), new BigInteger("1129"), new BigInteger("1151"), new BigInteger("1153"), new BigInteger("1163"), new BigInteger("1171"), new BigInteger("1181"), new BigInteger("1187"), new BigInteger("1193"), new BigInteger("1201"), new BigInteger("1213"), new BigInteger("1217"), new BigInteger("1223"), new BigInteger("1229"), new BigInteger("1231"), new BigInteger("1237"), new BigInteger("1249"), new BigInteger("1259"), new BigInteger("1277"), new BigInteger("1279"), new BigInteger("1283"), new BigInteger("1289"), new BigInteger("1291"), new BigInteger("1297"), new BigInteger("1301"), new BigInteger("1303"), new BigInteger("1307"), new BigInteger("1319"), new BigInteger("1321"), new BigInteger("1327"), new BigInteger("1361"), new BigInteger("1367"), new BigInteger("1373"), new BigInteger("1381"), new BigInteger("1399"), new BigInteger("1409"), new BigInteger("1423"), new BigInteger("1427"), new BigInteger("1429"), new BigInteger("1433"), new BigInteger("1439"), new BigInteger("1447"), new BigInteger("1451"), new BigInteger("1453"), new BigInteger("1459"), new BigInteger("1471"), new BigInteger("1481"), new BigInteger("1483"), new BigInteger("1487"), new BigInteger("1489"), new BigInteger("1493"), new BigInteger("1499"), new BigInteger("1511"), new BigInteger("1523"), new BigInteger("1531"), new BigInteger("1543"), new BigInteger("1549"), new BigInteger("1553"), new BigInteger("1559"), new BigInteger("1567"), new BigInteger("1571"), new BigInteger("1579"), new BigInteger("1583"), new BigInteger("1597"), new BigInteger("1601"), new BigInteger("1607"), new BigInteger("1609"), new BigInteger("1613"), new BigInteger("1619"), new BigInteger("1621"), new BigInteger("1627"), new BigInteger("1637"), new BigInteger("1657"), new BigInteger("1663"), new BigInteger("1667"), new BigInteger("1669"), new BigInteger("1693"), new BigInteger("1697"), new BigInteger("1699"), new BigInteger("1709"), new BigInteger("1721"), new BigInteger("1723"), new BigInteger("1733"), new BigInteger("1741"), new BigInteger("1747"), new BigInteger("1753"), new BigInteger("1759"), new BigInteger("1777"), new BigInteger("1783"), new BigInteger("1787"), new BigInteger("1789"), new BigInteger("1801"), new BigInteger("1811"), new BigInteger("1823"), new BigInteger("1831"), new BigInteger("1847"), new BigInteger("1861"), new BigInteger("1867"), new BigInteger("1871"), new BigInteger("1873"), new BigInteger("1877"), new BigInteger("1879"), new BigInteger("1889"), new BigInteger("1901"), new BigInteger("1907"), new BigInteger("1913"), new BigInteger("1931"), new BigInteger("1933"), new BigInteger("1949"), new BigInteger("1951"), new BigInteger("1973"), new BigInteger("1979"), new BigInteger("1987"), new BigInteger("1993"), new BigInteger("1997"), new BigInteger("1999"), new BigInteger("2003"), new BigInteger("2011"), new BigInteger("2017"), new BigInteger("2027"), new BigInteger("2029"), new BigInteger("2039"), new BigInteger("2053"), new BigInteger("2063"), new BigInteger("2069"), new BigInteger("2081"), new BigInteger("2083"), new BigInteger("2087"), new BigInteger("2089"), new BigInteger("2099"), new BigInteger("2111"), new BigInteger("2113"), new BigInteger("2129"), new BigInteger("2131"), new BigInteger("2137"), new BigInteger("2141"), new BigInteger("2143"), new BigInteger("2153"), new BigInteger("2161"), new BigInteger("2179"), new BigInteger("2203"), new BigInteger("2207"), new BigInteger("2213"), new BigInteger("2221"), new BigInteger("2237"), new BigInteger("2239"), new BigInteger("2243"), new BigInteger("2251"), new BigInteger("2267"), new BigInteger("2269"), new BigInteger("2273"), new BigInteger("2281"), new BigInteger("2287"), new BigInteger("2293"), new BigInteger("2297"), new BigInteger("2309"), new BigInteger("2311"), new BigInteger("2333"), new BigInteger("2339"), new BigInteger("2341"), new BigInteger("2347"), new BigInteger("2351"), new BigInteger("2357"), new BigInteger("2371"), new BigInteger("2377"), new BigInteger("2381"), new BigInteger("2383"), new BigInteger("2389"), new BigInteger("2393"), new BigInteger("2399"), new BigInteger("2411"), new BigInteger("2417"), new BigInteger("2423"), new BigInteger("2437"), new BigInteger("2441"), new BigInteger("2447"), new BigInteger("2459"), new BigInteger("2467"), new BigInteger("2473"), new BigInteger("2477"), new BigInteger("2503"), new BigInteger("2521"), new BigInteger("2531"), new BigInteger("2539"), new BigInteger("2543"), new BigInteger("2549"), new BigInteger("2551"), new BigInteger("2557"), new BigInteger("2579"), new BigInteger("2591"), new BigInteger("2593"), new BigInteger("2609"), new BigInteger("2617"), new BigInteger("2621"), new BigInteger("2633"), new BigInteger("2647"), new BigInteger("2657"), new BigInteger("2659"), new BigInteger("2663"), new BigInteger("2671"), new BigInteger("2677"), new BigInteger("2683"), new BigInteger("2687"), new BigInteger("2689"), new BigInteger("2693"), new BigInteger("2699"), new BigInteger("2707"), new BigInteger("2711"), new BigInteger("2713"), new BigInteger("2719"), new BigInteger("2729"), new BigInteger("2731"), new BigInteger("2741"), new BigInteger("2749"), new BigInteger("2753"), new BigInteger("2767"), new BigInteger("2777"), new BigInteger("2789"), new BigInteger("2791"), new BigInteger("2797"), new BigInteger("2801"), new BigInteger("2803"), new BigInteger("2819"), new BigInteger("2833"), new BigInteger("2837"), new BigInteger("2843"), new BigInteger("2851"), new BigInteger("2857"), new BigInteger("2861"), new BigInteger("2879"), new BigInteger("2887"), new BigInteger("2897"), new BigInteger("2903"), new BigInteger("2909"), new BigInteger("2917"), new BigInteger("2927"), new BigInteger("2939"), new BigInteger("2953"), new BigInteger("2957"), new BigInteger("2963"), new BigInteger("2969"), new BigInteger("2971"), new BigInteger("2999"), new BigInteger("3001"), new BigInteger("3011"), new BigInteger("3019"), new BigInteger("3023"), new BigInteger("3037"), new BigInteger("3041"), new BigInteger("3049"), new BigInteger("3061"), new BigInteger("3067"), new BigInteger("3079"), new BigInteger("3083"), new BigInteger("3089"), new BigInteger("3109"), new BigInteger("3119"), new BigInteger("3121"), new BigInteger("3137"), new BigInteger("3163"), new BigInteger("3167"), new BigInteger("3169"), new BigInteger("3181"), new BigInteger("3187"), new BigInteger("3191"), new BigInteger("3203"), new BigInteger("3209"), new BigInteger("3217"), new BigInteger("3221"), new BigInteger("3229"), new BigInteger("3251"), new BigInteger("3253"), new BigInteger("3257"), new BigInteger("3259"), new BigInteger("3271"), new BigInteger("3299"), new BigInteger("3301"), new BigInteger("3307"), new BigInteger("3313"), new BigInteger("3319"), new BigInteger("3323"), new BigInteger("3329"), new BigInteger("3331"), new BigInteger("3343"), new BigInteger("3347"), new BigInteger("3359"), new BigInteger("3361"), new BigInteger("3371"), new BigInteger("3373"), new BigInteger("3389"), new BigInteger("3391"), new BigInteger("3407"), new BigInteger("3413"), new BigInteger("3433"), new BigInteger("3449"), new BigInteger("3457"), new BigInteger("3461"), new BigInteger("3463"), new BigInteger("3467"), new BigInteger("3469"), new BigInteger("3491"), new BigInteger("3499"), new BigInteger("3511"), new BigInteger("3517"), new BigInteger("3527"), new BigInteger("3529"), new BigInteger("3533"), new BigInteger("3539"), new BigInteger("3541"), new BigInteger("3547"), new BigInteger("3557"), new BigInteger("3559"), new BigInteger("3571"), new BigInteger("3581"), new BigInteger("3583"), new BigInteger("3593"), new BigInteger("3607"), new BigInteger("3613"), new BigInteger("3617"), new BigInteger("3623"), new BigInteger("3631"), new BigInteger("3637"), new BigInteger("3643"), new BigInteger("3659"), new BigInteger("3671"), new BigInteger("3673"), new BigInteger("3677"), new BigInteger("3691"), new BigInteger("3697"), new BigInteger("3701"), new BigInteger("3709"), new BigInteger("3719"), new BigInteger("3727"), new BigInteger("3733"), new BigInteger("3739"), new BigInteger("3761"), new BigInteger("3767"), new BigInteger("3769"), new BigInteger("3779"), new BigInteger("3793"), new BigInteger("3797"), new BigInteger("3803"), new BigInteger("3821"), new BigInteger("3823"), new BigInteger("3833"), new BigInteger("3847"), new BigInteger("3851"), new BigInteger("3853"), new BigInteger("3863"), new BigInteger("3877"), new BigInteger("3881"), new BigInteger("3889"), new BigInteger("3907"), new BigInteger("3911"), new BigInteger("3917"), new BigInteger("3919"), new BigInteger("3923"), new BigInteger("3929"), new BigInteger("3931"), new BigInteger("3943"), new BigInteger("3947"), new BigInteger("3967"), new BigInteger("3989"), new BigInteger("4001"), new BigInteger("4003"), new BigInteger("4007"), new BigInteger("4013"), new BigInteger("4019"), new BigInteger("4021"), new BigInteger("4027"), new BigInteger("4049"), new BigInteger("4051"), new BigInteger("4057"), new BigInteger("4073"), new BigInteger("4079"), new BigInteger("4091"), new BigInteger("4093"), new BigInteger("4099"), new BigInteger("4111"), new BigInteger("4127"), new BigInteger("4129"), new BigInteger("4133"), new BigInteger("4139"), new BigInteger("4153"), new BigInteger("4157"), new BigInteger("4159"), new BigInteger("4177"), new BigInteger("4201"), new BigInteger("4211"), new BigInteger("4217"), new BigInteger("4219"), new BigInteger("4229"), new BigInteger("4231"), new BigInteger("4241"), new BigInteger("4243"), new BigInteger("4253"), new BigInteger("4259"), new BigInteger("4261"), new BigInteger("4271"), new BigInteger("4273"), new BigInteger("4283"), new BigInteger("4289"), new BigInteger("4297"), new BigInteger("4327"), new BigInteger("4337"), new BigInteger("4339"), new BigInteger("4349"), new BigInteger("4357"), new BigInteger("4363"), new BigInteger("4373"), new BigInteger("4391"), new BigInteger("4397"), new BigInteger("4409"), new BigInteger("4421"), new BigInteger("4423"), new BigInteger("4441"), new BigInteger("4447"), new BigInteger("4451"), new BigInteger("4457"), new BigInteger("4463"), new BigInteger("4481"), new BigInteger("4483"), new BigInteger("4493"), new BigInteger("4507"), new BigInteger("4513"), new BigInteger("4517"), new BigInteger("4519"), new BigInteger("4523"), new BigInteger("4547"), new BigInteger("4549"), new BigInteger("4561"), new BigInteger("4567"), new BigInteger("4583"), new BigInteger("4591"), new BigInteger("4597"), new BigInteger("4603"), new BigInteger("4621"), new BigInteger("4637"), new BigInteger("4639"), new BigInteger("4643"), new BigInteger("4649"), new BigInteger("4651"), new BigInteger("4657"), new BigInteger("4663"), new BigInteger("4673"), new BigInteger("4679"), new BigInteger("4691"), new BigInteger("4703"), new BigInteger("4721"), new BigInteger("4723"), new BigInteger("4729"), new BigInteger("4733"), new BigInteger("4751"), new BigInteger("4759"), new BigInteger("4783"), new BigInteger("4787"), new BigInteger("4789"), new BigInteger("4793"), new BigInteger("4799"), new BigInteger("4801"), new BigInteger("4813"), new BigInteger("4817"), new BigInteger("4831"), new BigInteger("4861"), new BigInteger("4871"), new BigInteger("4877"), new BigInteger("4889"), new BigInteger("4903"), new BigInteger("4909"), new BigInteger("4919"), new BigInteger("4931"), new BigInteger("4933"), new BigInteger("4937"), new BigInteger("4943"), new BigInteger("4951"), new BigInteger("4957"), new BigInteger("4967"), new BigInteger("4969"), new BigInteger("4973"), new BigInteger("4987"), new BigInteger("4993"), new BigInteger("4999"), new BigInteger("5003"), new BigInteger("5009"), new BigInteger("5011"), new BigInteger("5021"), new BigInteger("5023"), new BigInteger("5039"), new BigInteger("5051"), new BigInteger("5059"), new BigInteger("5077"), new BigInteger("5081"), new BigInteger("5087"), new BigInteger("5099"), new BigInteger("5101"), new BigInteger("5107"), new BigInteger("5113"), new BigInteger("5119"), new BigInteger("5147"), new BigInteger("5153"), new BigInteger("5167"), new BigInteger("5171"), new BigInteger("5179"), new BigInteger("5189"), new BigInteger("5197"), new BigInteger("5209"), new BigInteger("5227"), new BigInteger("5231"), new BigInteger("5233"), new BigInteger("5237"), new BigInteger("5261"), new BigInteger("5273"), new BigInteger("5279"), new BigInteger("5281"), new BigInteger("5297"), new BigInteger("5303"), new BigInteger("5309"), new BigInteger("5323"), new BigInteger("5333"), new BigInteger("5347"), new BigInteger("5351"), new BigInteger("5381"), new BigInteger("5387"), new BigInteger("5393"), new BigInteger("5399"), new BigInteger("5407"), new BigInteger("5413"), new BigInteger("5417"), new BigInteger("5419"), new BigInteger("5431"), new BigInteger("5437"), new BigInteger("5441"), new BigInteger("5443"), new BigInteger("5449"), new BigInteger("5471"), new BigInteger("5477"), new BigInteger("5479"), new BigInteger("5483"), new BigInteger("5501"), new BigInteger("5503"), new BigInteger("5507"), new BigInteger("5519"), new BigInteger("5521"), new BigInteger("5527"), new BigInteger("5531"), new BigInteger("5557"), new BigInteger("5563"), new BigInteger("5569"), new BigInteger("5573"), new BigInteger("5581"), new BigInteger("5591"), new BigInteger("5623"), new BigInteger("5639"), new BigInteger("5641"), new BigInteger("5647"), new BigInteger("5651"), new BigInteger("5653"), new BigInteger("5657"), new BigInteger("5659"), new BigInteger("5669"), new BigInteger("5683"), new BigInteger("5689"), new BigInteger("5693"), new BigInteger("5701"), new BigInteger("5711"), new BigInteger("5717"), new BigInteger("5737"), new BigInteger("5741"), new BigInteger("5743"), new BigInteger("5749"), new BigInteger("5779"), new BigInteger("5783"), new BigInteger("5791"), new BigInteger("5801"), new BigInteger("5807"), new BigInteger("5813"), new BigInteger("5821"), new BigInteger("5827"), new BigInteger("5839"), new BigInteger("5843"), new BigInteger("5849"), new BigInteger("5851"), new BigInteger("5857"), new BigInteger("5861"), new BigInteger("5867"), new BigInteger("5869"), new BigInteger("5879"), new BigInteger("5881"), new BigInteger("5897"), new BigInteger("5903"), new BigInteger("5923"), new BigInteger("5927"), new BigInteger("5939"), new BigInteger("5953"), new BigInteger("5981"), new BigInteger("5987"), new BigInteger("6007"), new BigInteger("6011"), new BigInteger("6029"), new BigInteger("6037"), new BigInteger("6043"), new BigInteger("6047"), new BigInteger("6053"), new BigInteger("6067"), new BigInteger("6073"), new BigInteger("6079"), new BigInteger("6089"), new BigInteger("6091"), new BigInteger("6101"), new BigInteger("6113"), new BigInteger("6121"), new BigInteger("6131"), new BigInteger("6133"), new BigInteger("6143"), new BigInteger("6151"), new BigInteger("6163"), new BigInteger("6173"), new BigInteger("6197"), new BigInteger("6199"), new BigInteger("6203"), new BigInteger("6211"), new BigInteger("6217"), new BigInteger("6221"), new BigInteger("6229"), new BigInteger("6247"), new BigInteger("6257"), new BigInteger("6263"), new BigInteger("6269"), new BigInteger("6271"), new BigInteger("6277"), new BigInteger("6287"), new BigInteger("6299"), new BigInteger("6301"), new BigInteger("6311"), new BigInteger("6317"), new BigInteger("6323"), new BigInteger("6329"), new BigInteger("6337"), new BigInteger("6343"), new BigInteger("6353"), new BigInteger("6359"), new BigInteger("6361"), new BigInteger("6367"), new BigInteger("6373"), new BigInteger("6379"), new BigInteger("6389"), new BigInteger("6397"), new BigInteger("6421"), new BigInteger("6427"), new BigInteger("6449"), new BigInteger("6451"), new BigInteger("6469"), new BigInteger("6473"), new BigInteger("6481"), new BigInteger("6491"), new BigInteger("6521"), new BigInteger("6529"), new BigInteger("6547"), new BigInteger("6551"), new BigInteger("6553"), new BigInteger("6563"), new BigInteger("6569"), new BigInteger("6571"), new BigInteger("6577"), new BigInteger("6581"), new BigInteger("6599"), new BigInteger("6607"), new BigInteger("6619"), new BigInteger("6637"), new BigInteger("6653"), new BigInteger("6659"), new BigInteger("6661"), new BigInteger("6673"), new BigInteger("6679"), new BigInteger("6689"), new BigInteger("6691"), new BigInteger("6701"), new BigInteger("6703"), new BigInteger("6709"), new BigInteger("6719"), new BigInteger("6733"), new BigInteger("6737"), new BigInteger("6761"), new BigInteger("6763"), new BigInteger("6779"), new BigInteger("6781"), new BigInteger("6791"), new BigInteger("6793"), new BigInteger("6803"), new BigInteger("6823"), new BigInteger("6827"), new BigInteger("6829"), new BigInteger("6833"), new BigInteger("6841"), new BigInteger("6857"), new BigInteger("6863"), new BigInteger("6869"), new BigInteger("6871"), new BigInteger("6883"), new BigInteger("6899"), new BigInteger("6907"), new BigInteger("6911"), new BigInteger("6917"), new BigInteger("6947"), new BigInteger("6949"), new BigInteger("6959"), new BigInteger("6961"), new BigInteger("6967"), new BigInteger("6971"), new BigInteger("6977"), new BigInteger("6983"), new BigInteger("6991"), new BigInteger("6997"), new BigInteger("7001"), new BigInteger("7013"), new BigInteger("7019"), new BigInteger("7027"), new BigInteger("7039"), new BigInteger("7043"), new BigInteger("7057"), new BigInteger("7069"), new BigInteger("7079"), new BigInteger("7103"), new BigInteger("7109"), new BigInteger("7121"), new BigInteger("7127"), new BigInteger("7129"), new BigInteger("7151"), new BigInteger("7159"), new BigInteger("7177"), new BigInteger("7187"), new BigInteger("7193"), new BigInteger("7207"), new BigInteger("7211"), new BigInteger("7213"), new BigInteger("7219"), new BigInteger("7229"), new BigInteger("7237"), new BigInteger("7243"), new BigInteger("7247"), new BigInteger("7253"), new BigInteger("7283"), new BigInteger("7297"), new BigInteger("7307"), new BigInteger("7309"), new BigInteger("7321"), new BigInteger("7331"), new BigInteger("7333"), new BigInteger("7349"), new BigInteger("7351"), new BigInteger("7369"), new BigInteger("7393"), new BigInteger("7411"), new BigInteger("7417"), new BigInteger("7433"), new BigInteger("7451"), new BigInteger("7457"), new BigInteger("7459"), new BigInteger("7477"), new BigInteger("7481"), new BigInteger("7487"), new BigInteger("7489"), new BigInteger("7499"), new BigInteger("7507"), new BigInteger("7517"), new BigInteger("7523"), new BigInteger("7529"), new BigInteger("7537"), new BigInteger("7541"), new BigInteger("7547"), new BigInteger("7549"), new BigInteger("7559"), new BigInteger("7561"), new BigInteger("7573"), new BigInteger("7577"), new BigInteger("7583"), new BigInteger("7589"), new BigInteger("7591"), new BigInteger("7603"), new BigInteger("7607"), new BigInteger("7621"), new BigInteger("7639"), new BigInteger("7643"), new BigInteger("7649"), new BigInteger("7669"), new BigInteger("7673"), new BigInteger("7681"), new BigInteger("7687"), new BigInteger("7691"), new BigInteger("7699"), new BigInteger("7703"), new BigInteger("7717"), new BigInteger("7723"), new BigInteger("7727"), new BigInteger("7741"), new BigInteger("7753"), new BigInteger("7757"), new BigInteger("7759"), new BigInteger("7789"), new BigInteger("7793"), new BigInteger("7817"), new BigInteger("7823"), new BigInteger("7829"), new BigInteger("7841"), new BigInteger("7853"), new BigInteger("7867"), new BigInteger("7873"), new BigInteger("7877"), new BigInteger("7879"), new BigInteger("7883"), new BigInteger("7901"), new BigInteger("7907"), new BigInteger("7919")
    };

    private static BigInteger smallPrimeDivisor(BigInteger N) {
        for(BigInteger p : smallPrimes) {
            if (N.mod(p) == ZERO)
                return p;
        }
        return ZERO;
    }

    public static BigInteger divisor(BigInteger N) {
        BigInteger c = new BigInteger(N.bitLength(), random);
 
        BigInteger x = new BigInteger(N.bitLength(), random);
        BigInteger y = x;
        BigInteger divisor = ONE;
 
        // check if even
        if (N.mod(TWO).compareTo(ZERO) == 0) return TWO;
 
        while((divisor.compareTo(ONE)) == 0) {
            x = f(x,c,N);
            y = f(f(y,c,N),c,N);
            divisor = x.subtract(y).gcd(N);
        }
 
        return divisor;
    }

    public static void factor(BigInteger N) {
        if (N.compareTo(ONE) == 0) return;
        
        if (N.isProbablePrime(10)) { 
            factors[prlen++] = N;
			return;
        }
        
        BigInteger p = smallPrimeDivisor(N);
        BigInteger d = (p != ZERO) ? p : divisor(N);
        
        factor(d);
        factor(N.divide(d)); 
    }

    public static void main(String[] args) throws IOException{
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        long test = in.readLong();
        while(test-- > 0 ){
            String s;
    		BigInteger n;
    		int i, j;
    		boolean first;
    		BigInteger res = BigInteger.ONE;
    	    BigInteger temp;
            BigInteger N = BigInteger.valueOf(in.readLong());
            if(N.compareTo(ONE) == 0){
    		    out.println(0);
    		    continue;
            }
    		prlen = 0;
    		factor(N);
    		Arrays.sort(factors,0, prlen);
    		for(i = 0; i < prlen; i++) {
    			n = factors[i]; j = i;
    			while(j < prlen && factors[j].equals(n)) j++;
    			temp = n.pow(j-i+1);
    			temp = temp.subtract(ONE);
    			temp = temp.divide(n.subtract(ONE));
    			res = res.multiply(temp);
    			i = j - 1;
    			
    		}
    	    out.println(res.subtract(N));
        }
        out.close();
    }
}

class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(outputStream);
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void println(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}


