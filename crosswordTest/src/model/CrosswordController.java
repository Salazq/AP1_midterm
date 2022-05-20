package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {

		int count=1;

		crossword= new Cell [puzzle.length][puzzle[0].length];

		for (int i=0; i<puzzle.length; i++){

			for (int e=0; e<puzzle[0].length; e++){

				if (puzzle[i][e].equals(" ")){

					crossword[i][e]= new Cell (CellType.BLACK,puzzle[i][e], count);
				}
				else{
					crossword[i][e]= new Cell (CellType.CLOSED,puzzle[i][e], count);
				}
				count ++;
			}
		}

	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {

		String out="\nThere is no closed cell with letter:" + letter + "\n";
		boolean found=false;

		for (int i=0; i<crossword.length && found==false; i++){

			for (int e=0; e<crossword[0].length && found==false; e++){

				if (crossword[i][e].getState().equals(CellType.CLOSED) && crossword[i][e].getLetter().equals(letter)){

					out= "\nThere is a letter:"+ letter+ " in cell:" + crossword[i][e].getNumber()+"\n";
					crossword[i][e].setState(CellType.OPEN);
					found= true;
				}
			}
		}
		return out;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {

		String out="\nLetter:" + letter + " is not in cell:" +num+"\n";
		boolean found=false;

		for (int i=0; i<crossword.length && found==false; i++){

			for (int e=0; e<crossword[0].length && found==false; e++){

				if (crossword[i][e].getNumber()== num && crossword[i][e].getLetter().equals(letter)){

					out= "\nLetter:" + letter + " is in cell:" +num+"\n";
					crossword[i][e].setState(CellType.OPEN);
					found= true;
				}
			}
		}
		
		return out;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else if (actual.getState()==CellType.OPEN) {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}else{
						numbers += " "+actual.getNumber() +"   ";
						letters += "      ";

					}

				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else if (actual.getState()==CellType.OPEN) {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}else{
						numbers += " "+actual.getNumber() +"    ";
						letters += "      ";

					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
