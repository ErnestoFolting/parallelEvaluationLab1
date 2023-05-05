using Newtonsoft.Json;
using System.Text;


namespace Lab8.Client
{
    public class RequestSender
    {
        public async Task sendWithSize()
        {
            using var client = new HttpClient();

            Console.Write("Input the matrix size: ");
            int matrixSize = Convert.ToInt32(Console.ReadLine());

            var data = new { Size = matrixSize };
            var json = JsonConvert.SerializeObject(data);
            var bodyContent = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await client.PostAsync("https://localhost:7135/api/MatrixMultiplier", bodyContent);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine(content);
            }
            else
            {
                Console.WriteLine($"Error: {response.StatusCode}");
            }
        }

        public async Task sendWithMatrix()
        {
            Console.Write("Input the matrix size: ");
            int matrixSize = Convert.ToInt32(Console.ReadLine());

            float[,] matrix1 = MatrixHelper.generateMatrix(matrixSize);
            float[,] matrix2 = MatrixHelper.generateMatrix(matrixSize);

            string matrix1String = string.Join(",", matrix1.Cast<float>());
            string matrix2String = string.Join(",", matrix2.Cast<float>());

            var data = new { size = matrixSize, matrix1String = matrix1String, matrix2String = matrix2String };
            var json = JsonConvert.SerializeObject(data);
            var bodyContent = new StringContent(json, Encoding.UTF8, "application/json");

            using var client = new HttpClient();
            var response = await client.PostAsync("https://localhost:7135/api/MatrixMultiplier/with-matrix", bodyContent);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                Console.WriteLine(content);
            }
            else
            {
                Console.WriteLine($"Error: {response.StatusCode}");
            }
        }
    }
}
