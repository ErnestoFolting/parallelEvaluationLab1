using Lab8.Helpers;
using Lab8.Services.Multiplier;
using Microsoft.AspNetCore.Mvc;
using System.Numerics;

namespace Lab8.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MatrixMultiplierController : ControllerBase
    {
        private IMatrixMultiplier _multiplier;
        public MatrixMultiplierController(IMatrixMultiplier multiplier)
        {
            _multiplier = multiplier;
        }
        [HttpPost]
        public IActionResult Post([FromBody] MatrixSizeData data)
        {
            int matrixSize = data.size;
            float[,] matrA = MatrixHelper.generateMatrix(matrixSize);
            float[,] matrB = MatrixHelper.generateMatrix(matrixSize);
            float[] firstRow = _multiplier.multiply(matrixSize, matrA, matrB);
            string row = string.Join(",", firstRow.Cast<float>());
            //return Ok($"Generated on back-end\nMatrix size is {matrixSize}\nFirst row: {row}");
            return Ok($"Generated on back-end\nMatrix size is {matrixSize}");
        }


        [HttpPost("with-matrix")]
        public IActionResult PostWithMatrix([FromBody] MatrixData data)
        {
            int size = data.size;
            float[,] matrix1 = MatrixHelper.readMatrixFromString(data.matrix1String, size);
            float[,] matrix2 = MatrixHelper.readMatrixFromString(data.matrix2String, size);
            float[] firstRow = _multiplier.multiply(size, matrix1, matrix2);
            string row = firstRow.ToString();
            //return Ok($"Generated on front-end\nMatrix size is {size}\nFirst row: {row}");
            return Ok($"Generated on front-end\nMatrix size is {size}");
        }
    }
}
