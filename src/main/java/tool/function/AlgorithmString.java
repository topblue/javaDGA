package tool.function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;


public class AlgorithmString {
//	public static void main(String[] args) throws JSONException {
//		AlgorithmString algo = new AlgorithmString();
//	}
	
	/**
	 * @see entropy運算
	 * @param double e,double l
	 * @category e=element,s=size
	 * @return r=result
	 * **/
	public double entropy(double e,double s){
		double r =e/s*Math.log10(e/s);
//		System.out.println(r+"\t=\t"+e+"/"+s+"*log("+e+"/"+s+")");
		return r;
	}
	/**
	 * @see 變異數演算法Variance
	 * @param m個數值
	 * @param n是平均值
	 * @param 所有domain的值放在arraylist
	 * @return 變數數的值
	 * ****/
	public double variance(ArrayList<Double> arr,double s,double n){
		double result = 0.0;
		for(double var:arr){
			double sub = double_sub(var,n);	//xi值平均值相減
			double mul = mul(sub,sub);	//算平方
			result = result + mul;	//累加
//			System.out.println("相減："+sub+"，接下來平方："+mul);
			System.out.println(mul);
		}
		System.out.println("累積："+result);
		result = (result/s);	//計算平均值
		System.out.println("平均值："+result);
		return result;
	}

	/**
	 * @see 求整數(int)型態的平均值
	 * @param arrInt = ArrayList<int>
	 * @param n是平均值
	 * @param 所有domain的值放在arraylist
	 * @return double
	 * ****/
	public double average_int(ArrayList<Double> arrInt){
		double result = 0.0 , total = 0.0;
		for(double d:arrInt){
			total = total + d;
		}
		result = (total / arrInt.size());
		return result;
	}
	

	/**
	 * @see double相加
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 回傳double的兩數值加總
	 * **/
	public double double_add (double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.valueOf(v1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	* @see double 精確的減法運算。
	* @param v1 被減數
	* @param v2 減數
	* @return 兩個參數的差
	*/
	public double double_sub(double v1,double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	* @see double 提供（相對）精確的除法運算，當發生除不盡的情況時，精確到小數點以後10位元，以後的數字四捨五入。
	* @see v1/v2
	* @param v1 被除數
	* @param v2 除數
	* @return 兩個參數的商
	*/
	public double div(double v1,double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,10,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	* @see double 提供精確的乘法運算。
	* @param v1 被乘數
	* @param v2 乘數
	* @return 兩個參數的積
	*/
	public double mul(double v1,double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	
	//計算 log();
	double  math_log(Map<String, String> map_total){
		ArrayList<Double> math = new ArrayList<Double>();
		double a1 =0;  //取得全部加總值
	double w = map_total.size();  //全部筆數
	int f = 0;
	for (String key : map_total.keySet()) {
		map_total.get(key);
		double k= Integer.parseInt(map_total.get(key)); //出現次數
	double a;  //取得每筆加總數值
				for (int i = 0; i < k; i++) {
					a=k/w*Math.log10(k/w);
					if (f==0) {
						a1=a;
						f++;
					}else {
						a1=double_add(a1, a);
					}
				}
			}
			return a1;
	}
	
}
