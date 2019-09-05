/**
 * @author Karol Pawlak
 * @date May 2019
 * @description Shortest path algorithm
 *  
 */

public class RobotMoving {
	//attributes
	private int size = 5;
	private double matrix[][] = new double[size][size];
	private String directions[][] = new String[size][size];
	
	
	//price per direction
	private double right = 1;
	private double diagonal = 5.3;
	private double down = 5.5;
	
	public double runRobot() 
	{
		//starting point
		matrix[0][0] = 0;
		directions[0][0] = "X";
		
		double cost = 0;
		
		for(int j = 1; j < size; j++)
		{
			//go right
			matrix[0][j] = matrix[0][j-1] + right;
			directions[0][j] = "->";
		}
		for(int i = 1; i < size; i++)
		{
			//go down
			matrix[i][0] = matrix[i-1][0] + down; 
			directions[i][0] = " | ";
		
			for(int j = 1; j < size; j++)
			{
				//decide
				double nextDown = matrix[i - 1][j] + down;
				double nextRight = matrix[i][j - 1] + right;
				double nextDiag = matrix[i - 1][j - 1] + diagonal;
				//go down
				if (nextDown <= nextRight && nextDown <= nextDiag)
				{
					matrix[i][j] = nextDown;
					directions[i][j] = " | ";
					System.out.println("Go down from \t\t(" + (i - 1) + " " + j + ") to (" + i + " " + j + ") for the price " + down);
				}
				//go right
				else if (nextRight <= nextDown && nextRight <= nextDiag)
				{
					matrix[i][j] = nextRight;
					directions[i][j] = "->";
					System.out.println("Go right from \t\t(" + i + " " + (j - 1) + ") to (" + i + " " + j + ") for the price " + right);
				}
				else if (nextDiag <= nextRight && nextDiag <= nextDown)
				//go diagonally
				{
					matrix[i][j] = nextDiag;
					directions[i][j] = " \\ ";
					System.out.println("Go diagonally from \t(" + (i - 1) + " " + (j - 1) + ") to (" + i + " " + j + ") for the price " + diagonal);
				}
				cost = matrix[size - 1][size -1];
			}
		}
		return cost;
	}
	
	public void printSolution() 
	{
		System.out.println("\n\nRobot directions :");
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				String img = directions[i][j];
				System.out.print(img);
			}
			System.out.print("\n");
		}
		
		System.out.println("\nSolutions for every postion:");
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				double val = Math.round(matrix[i][j] * 100.0) /100.0;
				System.out.print(" " + val + " ");
			}
			System.out.print("\n");
		}
		
	}
	
	public static void main(String[] args) 
	{
		RobotMoving x = new RobotMoving();
		double res = x.runRobot();
		System.out.println("\nTotal cost to finish point = " + Math.round(res * 100.0) /100.0);
		x.printSolution();
	}

}
