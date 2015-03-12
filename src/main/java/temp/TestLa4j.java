package temp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.la4j.LinearAlgebra;
import org.la4j.matrix.Matrices;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.matrix.functor.MatrixFunction;
import org.la4j.matrix.sparse.CCSMatrix;
import org.la4j.matrix.sparse.CRSMatrix;
import org.la4j.vector.Vector;

import bi.gram.BigramExpandMethod;

public class TestLa4j {

	static String outputFilename = "data/bigram/bigramOutput.txt";	//計算好的陣列名稱
	static String testLa4jOutputFile = "data/bigram/testLa4jOutputFile.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
//		sparseMatrixMatrixMarke();
		matrixMultiply();
		
	}
	static void  sparseMatrixMatrixMarke() throws FileNotFoundException{
		bi.gram.BigramExpandMethod bem = new bi.gram.BigramExpandMethod();
		Matrix a = new CCSMatrix(Matrices.asMatrixMarketSource(
                new FileInputStream(outputFilename)));
		bem.cleanFile(testLa4jOutputFile);
		bem.resultToWrite(testLa4jOutputFile,a.toString());
//		System.out.println(a);
	}
	static void matrixMultiply(){
		// We want CCS matrix
		Matrix a = new CCSMatrix(new double[][] {
			   { 1.0, 0.0, 3.0 },
			   { 4.0, 0.0, 6.0 },
			   { 0.0, 5.0, 6.0 },
			   { 7.0, 8.0, 9.0 }
		});
		// We want 2D array matrix
		Matrix b = new Basic2DMatrix(new double[][] {
			   { 1.0, 2.0, 3.0 ,1.0},
			   { 4.0, 0.0, 6.0 ,2.0},
			   { 7.0, 0.0, 9.0 ,0}
		});
		// Multiply 'a' by 'b'
		Matrix c = a.multiply(b);
		
		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);

		System.out.println("ddddddddd");
		Matrix d = a.copy(LinearAlgebra.CRS_FACTORY);

		System.out.println(a.columns());
		System.out.println(a.rows());
		for(int i=0;i<a.rows();i++){
			for(int j=0;j<a.columns();j++){
				if(a.get(i, j)>0){
					System.out.println(i+","+j+","+a.get(i, j));
				}
			}
		}
	}

}
