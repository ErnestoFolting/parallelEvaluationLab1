import java.util.List;

public class ConcurrentMultiplier implements IMatrixMultiplier{
    @Override
    public Result multiply(List<List<Double>> matr1, List<List<Double>> matr2) {
        Result result = new Result(matr1.size());
        for (int i = 0; i < matr1.size(); i++) {
            for (int j = 0; j < matr1.size(); j++) {
                double sum = 0.0;
                for (int k = 0; k < matr1.size(); k++) {
                    sum += matr1.get(i).get(k) *matr2.get(k).get(j);
                }
                result.put(i,j,sum);
            }
        }
        return result;
    }
}
