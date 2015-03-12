package bi.gram;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.la4j.matrix.Matrices;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.sparse.CCSMatrix;

public class Multiply_SparseMatrix {
/**
 * 資料格式須符合下列四行格式，此格式為被讀取檔案的標頭
 * %%MatrixMarket matrix coordinate real general
 * % rows columns non-zero-values
 * 1579 1136 46952
 * % row column value
 * **/

	String A_SparseMatrixFileName = "data/bigram/bigramOutput.txt";	//計算好的陣列名稱
	String B_SparseMatrixFileName = "data/bigram/bigramOutputTransposeMatrix.txt";	//計算好的陣列名稱
//	String A_Multiply_B_MatrixFileName = "data/bigram/A_Multiply_B_Matrix.txt";	//計算好的陣列名稱
//	String A_Multiply_B_SparseMatrixFileName = "data/bigram/AMultiplyB_ToSparseMatrix.txt";	//計算好的陣列名稱
	String B_Multiply_A_SparseMatrixFileName = "data/bigram/BMultiplyA_ToSparseMatrix.txt";	//計算好的陣列名稱
	
	public static void main(String[] args) throws FileNotFoundException {
		bi.gram.BigramExpandMethod bem = new bi.gram.BigramExpandMethod();
		Multiply_SparseMatrix myclass = new Multiply_SparseMatrix();
		Matrix A_Matrix = myclass.sparseMatrixToMatrixMarke(myclass.A_SparseMatrixFileName);
		Matrix B_Matrix = myclass.sparseMatrixToMatrixMarke(myclass.B_SparseMatrixFileName);
//		矩陣相乘
//		Matrix A_Multiply_B = myclass.multiplyMatrix(A_Matrix, B_Matrix);
		Matrix B_Multiply_A = myclass.multiplyMatrix(B_Matrix, A_Matrix);
		
//		注意是資料移除的方法
		bem.cleanFile(myclass.B_Multiply_A_SparseMatrixFileName);
		
//		轉換為 Sparse Matrix
		for(int i=0;i<B_Multiply_A.rows();i++){
			for(int j=0;j<B_Multiply_A.columns();j++){
				if(B_Multiply_A.get(i, j)>0){
					String result = i+","+j+","+B_Multiply_A.get(i, j);
					bem.resultToWrite(myclass.B_Multiply_A_SparseMatrixFileName, result);
				}
			}
		}
	}
	
//	稀疏矩陣轉為正常矩陣，原來的稀疏矩陣用空格分隔
	Matrix sparseMatrixToMatrixMarke(String path) throws FileNotFoundException{
		Matrix matrix = new CCSMatrix(Matrices.asMatrixMarketSource(
                new FileInputStream(path)));
		return matrix;
	}

//	a *b 矩陣
	Matrix multiplyMatrix(Matrix first,Matrix secound){
		Matrix matrix = first.multiply(secound);
		return matrix;
	}

}
