package bi.gram;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;



public class MahoutCF_itemBase_userBase {

	public static void main(String[] args) throws Exception {
		MahoutCF_itemBase_userBase myclass = new MahoutCF_itemBase_userBase();
		String path = "data/bigram/bigramOutput_similarity.txt";
//		myclass.itemBaseCF(path);
		myclass.userBase(path);
	}
	
	void itemBaseCF(String path) throws Exception {
		DataModel model = new FileDataModel(new File(path));
		ItemSimilarity sim = new LogLikelihoodSimilarity(model);
		Recommender recommender= new GenericItemBasedRecommender(model, sim);
		LongPrimitiveIterator iter = model.getUserIDs();
		while (iter.hasNext()) {
			long uid = iter.nextLong();
			List<RecommendedItem> list = recommender.recommend(uid,3);
			System.out.printf("uid:%s", uid);
			for (RecommendedItem ritem : list) {
			    System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
			}
		    System.out.println();
		}
	}
	void userBase(String path) throws Exception{
		int NEIGHBORHOOD_NUM =3;//邻居数目
		int RECOMMENDER_NUM = 7;//推荐物品数目
		
		DataModel model =new FileDataModel(new File(path));//FileDataModel要求文件数据存储格式（必须用“，”分割）：userID,itemID[,preference[,timestamp]]
		
		UserSimilarity user = new PearsonCorrelationSimilarity(model);
		NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
		Recommender r = new GenericUserBasedRecommender(model, neighbor, user);
		LongPrimitiveIterator iter = model.getUserIDs();
		
		while (iter.hasNext()) {
			long uid = iter.nextLong();
			List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
			System.out.printf("uid:%s", uid);
			for (RecommendedItem ritem : list) {
				System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
			}
			System.out.println();
		}
	}
}


