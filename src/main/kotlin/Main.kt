fun main() { //Main function
    println("Welcome to my matrix calculator!")
    while (true) { //Loop to guarantee user inputs A or M
        println("Would you like to [A]dd or [M]ultiply matrices together? ")
        val choice = readln()
        if (choice.lowercase() == "a") {
            addChoice() //Calls matrix addition function
            break
        } else if (choice.lowercase() == "m") {
            multChoice() //Calls matrix multiplication function
            break
        } else {
            println("Please enter either A for addition or M for multiplication.")
            continue
        }
    }
}

fun multChoice(){
    //Sets variables - Can't figure out how to initiate variable inside while loop
    var rowA: Int
    var colA: Int
    var rowB: Int
    var colB: Int
    while (true){ //Loop that verifies that the matrices are multiple
        rowA = readInt("Enter the number of rows for matrix A: ")
        colA = readInt("Enter the number of columns for matrix A: ")
        rowB = readInt("Enter the number of rows for matrix B: ")
        colB = readInt("Enter the number of columns for matrix B: ")
        if (rowA <= 0 || rowB <= 0 || colA <= 0 || colB <= 0){ //Error that prevents the dimensions to be less than 0
            println("Error: Values must be greater than 0")
        }
        else if (colA == rowB){
            break
        }
        else{
            println("Error: Columns of matrix A must equal rows of matrix B.")
        }
    }
    val matrixA = Array(rowA) { IntArray(colA) } //Uses double array to create matrix
    println("Fill in matrix A")
    fillMatrix(matrixA) //Calls function to fill in matrix
    val matrixB = Array(rowB) { IntArray(colB) }
    println("Fill in matrix B")
    fillMatrix(matrixB)
    val matrixC = matrixMultiplication(matrixA, matrixB) //Multiplies matrices together
    printMatrix(matrixC) //Prints matrix
}

fun readInt(prompt: String): Int { //Function that prints and writes at the same time. Guarantees that return is an integer
    while (true){
        try{
            print(prompt)
            return readln().toInt()
        } catch(exception: NumberFormatException){ //Used to catch error to prevent a non integer number from being returned
            println("Please enter a valid number")
            continue
        }
    }
}

fun fillMatrix(matrix: Array<IntArray>) { //Loops through rows and columns to fill in matrix
    for (i in matrix.indices){ //Gets row index
        for (j in matrix[i].indices){ //Gets column index
            matrix[i][j] = readInt("Please enter the value in row ${i+1} and column ${j+1}: ")
        }
    }
}

fun matrixMultiplication(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> { //Multiplies matrices together
    val c = Array(a.size) { IntArray(b[0].size)} //Creates array based off dimensions of new matrices
    for (i in a.indices) { //Gets index of row of matrix A
        for (j in b[0].indices){ //Gets index of column of matrix B
            var sum = 0 //Sum of entries
            for (k in a[0].indices){
                sum += a[i][k] * b[k][j] //Sums the products of the corresponding elements
            }
            c[i][j] = sum //Inserts sum of products into new matrix
        }
    }
    return c //Returns matrix
}

fun printMatrix(matrix: Array<IntArray>) { //Prints out the completed matrix
    matrix.forEach{ row -> //Goes through each row
        println("")
        print("|")
        row.forEach{value -> //Goes through each column in the row
            print("$value ")
        }
        print("|")
    }
}

fun addChoice() { //Other choice for user. Adds matrices together
    var rowA: Int
    var colA: Int
    var rowB: Int
    var colB: Int
    while (true){ //Loops until matrices are the same size
        rowA = readInt("Enter the number of rows for matrix A: ")
        colA = readInt("Enter the number of columns for matrix A: ")
        rowB = readInt("Enter the number of rows for matrix B: ")
        colB = readInt("Enter the number of columns for matrix B: ")
        if (rowA <= 0 || rowB <= 0 || colA <= 0 || colB <= 0){ //Checks to see if all dimensions are valid
            println("Error: Dimensions must be greater than 0")
        }
        else if (rowA == rowB && colA == colB){
            break
        }
        else{
            print("Error: Matrix size must be equivalent")
            println("")
        }
    }
    val matrixA = Array(rowA) { IntArray(colA) } //Same as matrix multiplication but with addition function instead
    println("Fill in matrix A")
    fillMatrix(matrixA)
    val matrixB = Array(rowB) { IntArray(colB) }
    println("Fill in matrix B")
    fillMatrix(matrixB)
    val matrixC = matrixAddition(matrixA, matrixB)
    printMatrix(matrixC)
}

fun matrixAddition(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> { //Adds matrices together
    val c = Array(a.size) { IntArray(a[0].size)} //Creates new matrix that's the result of the summation
    for (i in a.indices){ //Loops through each row
        for (j in a[0].indices){ //Loops through each column
            c[i][j] = a[i][j] + b[i][j] //Adds each corresponding value
        }
    }
    return c //Returns matrix
}