package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Sebastian Manza
 * @author Samuel A. Rebelsky
 *
 * @param <T> The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /** The array that will be storing everything. */
  private T[][] matrix;

  /** The height of the array. */
  private int matrixHeight = 0;

  /** The width of the array. */
  private int matrixWidth = 0;

  /** The default value. */
  private T matrixDef;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the given value as the default.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   * @param def The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) throws NegativeArraySizeException {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if

    /* Create a new array and set width and height */
    this.matrix = (T[][]) new Object[width][height];
    this.matrixWidth = width;
    this.matrixHeight = height;
    this.matrixDef = def;

    /* Initialize all the values to be def. */
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        this.matrix[i][j] = def;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with null as the default value.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) throws IndexOutOfBoundsException {
    if ((row >= this.matrixHeight) || (col >= this.width) || (row < 0) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if
    return matrix[col][row];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   * @param val The value to set.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) throws IndexOutOfBoundsException {
    if ((row >= this.matrixHeight) || (col >= this.matrixWidth)) {
      throw new IndexOutOfBoundsException();
    } // if
    matrix[col][row] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.matrixHeight;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.matrixWidth;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row) throws IndexOutOfBoundsException {
    if (row < 0 || row > this.matrixHeight) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] tempArr = (T[][]) new Object[this.matrixWidth][this.matrixHeight + 1];
    for (int i = 0; i < this.matrixWidth; i++) {
      for (int j = 0; j < this.matrixHeight + 1; j++) {
        if (j == row) {
          tempArr[i][j] = this.matrixDef;
        } else {
          if (j < row) {
            tempArr[i][j] = this.matrix[i][j];
          } else {
            tempArr[i][j] = this.matrix[i][j - 1];
          } // if/else
        } // if/else
      } // for
    } // for
    this.matrixHeight++;
    this.matrix = tempArr;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row The number of the row to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   * @throws ArraySizeException If the size of vals is not the same as the width of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row, T[] vals) throws ArraySizeException, IndexOutOfBoundsException {
    if (this.matrixWidth != vals.length) {
      throw new ArraySizeException();
    } // if
    if (row < 0 || row > this.matrixHeight) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] tempArr = (T[][]) new Object[this.matrixWidth][this.matrixHeight + 1];
    for (int i = 0; i < this.matrixWidth; i++) {
      for (int j = 0; j < this.matrixHeight + 1; j++) {
        if (j == row) {
          tempArr[i][j] = vals[i];
        } else {
          if (j < row) {
            tempArr[i][j] = this.matrix[i][j];
          } else {
            tempArr[i][j] = this.matrix[i][j - 1];
          } // if/else
        } // if/else
      } // for
    } // for
    this.matrixHeight++;
    this.matrix = tempArr;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col) throws IndexOutOfBoundsException {
    if (col < 0 || col > this.matrixWidth) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] tempArr = (T[][]) new Object[this.matrixWidth + 1][this.matrixHeight];
    for (int i = 0; i < this.matrixWidth + 1; i++) {
      for (int j = 0; j < this.matrixHeight; j++) {
        if (i == col) {
          tempArr[i][j] = this.matrixDef;
        } else {
          if (i < col) {
            tempArr[i][j] = this.matrix[i][j];
          } else {
            tempArr[i][j] = this.matrix[i - 1][j];
          } // if/else
        } // if/else
      } // for
    } // for
    this.matrixWidth++;
    this.matrix = tempArr;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col The number of the column to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   * @throws ArraySizeException If the size of vals is not the same as the height of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col, T[] vals) throws ArraySizeException, IndexOutOfBoundsException {
    if (col < 0 || col > this.matrixWidth) {
      throw new IndexOutOfBoundsException();
    } // if
    if (this.matrixHeight != vals.length) {
      throw new ArraySizeException();
    } // if
    T[][] tempArr = (T[][]) new Object[this.matrixWidth + 1][this.matrixHeight];
    for (int i = 0; i < this.matrixWidth + 1; i++) {
      for (int j = 0; j < this.matrixHeight; j++) {
        if (i == col) {
          tempArr[i][j] = vals[j];
        } else {
          if (i < col) {
            tempArr[i][j] = this.matrix[i][j];
          } else {
            tempArr[i][j] = this.matrix[i - 1][j];
          } // if/else
        } // if/else
      } // for
    } // for
    this.matrixWidth++;
    this.matrix = tempArr;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than or equal to the
   *         height.
   */
  @SuppressWarnings("unchecked")
  public void deleteRow(int row) throws IndexOutOfBoundsException {
    if ((row < 0) || (row >= this.matrixHeight)) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] tempArr = (T[][]) new Object[this.matrixWidth][this.matrixHeight - 1];
    for (int i = 0; i < matrixHeight - 1; i++) {
      for (int j = 0; j < this.matrixWidth; j++) {
        if (i >= row) {
          tempArr[j][i] = this.matrix[j][i + 1];
        } else {
          tempArr[j][i] = this.matrix[j][i];
        } // if/else
      } // for
    } // for
    this.matrixHeight--;
    this.matrix = tempArr;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than or equal to the
   *         width.
   */
  @SuppressWarnings("unchecked")
  public void deleteCol(int col) {
    if ((col < 0) || (col >= this.matrixWidth)) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] tempArr = (T[][]) new Object[this.matrixWidth - 1][this.matrixHeight];
    for (int i = 0; i < this.matrixWidth; i++) {
      for (int j = 0; j < this.matrixHeight; j++) {
        if (i < col) {
          tempArr[i][j] = this.matrix[i][j];
        } else if (i > col) {
          tempArr[i - 1][j] = this.matrix[i][j];
        } // if/else
      } // for
    } // for
    this.matrixWidth--;
    this.matrix = tempArr;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow The top edge / row to start with (inclusive).
   * @param startCol The left edge / column to start with (inclusive).
   * @param endRow The bottom edge / row to stop with (exclusive).
   * @param endCol The right edge / column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val)
      throws IndexOutOfBoundsException {
    if ((startRow < 0) || (startCol < 0)
        || (endRow > this.matrixHeight) || (endCol > this.matrixWidth)) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = startCol; i < endCol; i++) {
      for (int j = startRow; j < endRow; j++) {
        this.matrix[i][j] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow The row to start with (inclusive).
   * @param startCol The column to start with (inclusive).
   * @param deltaRow How much to change the row in each step.
   * @param deltaCol How much to change the column in each step.
   * @param endRow The row to stop with (exclusive).
   * @param endCol The column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol, int endRow,
      int endCol, T val) throws IndexOutOfBoundsException {
    if (startRow < 0 || startCol < 0 || endRow > this.matrixHeight || endCol > this.matrixHeight) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = startRow, j = startCol; ((i < endRow) && (j < endCol)); i += deltaRow, j +=
        deltaCol) {
      this.matrix[j][i] = val;
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual elements are mutable,
   * mutating them in one matrix may affect the other matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    Matrix<T> newMatrix = this;
    return newMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other The object to compare.
   *
   * @return true if the other object is a matrix with the same width, height, and equal elements;
   *         false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix otherMatrix = (Matrix) other;
      if (this.matrixWidth == otherMatrix.width() && this.matrixHeight == otherMatrix.height()) {
        for (int i = 0; i < this.matrixWidth; i++) {
          for (int j = 0; j < this.matrixHeight; j++) {
            if (!this.get(j, i).equals(otherMatrix.get(j, i))) {
              return false;
            } // if
          } // for
        } // for
        return true;
      } // if
    } // if
    return false;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object that implements `equals` is
   * expected to implement `hashCode` and ensure that the hash codes for two equal objects are the
   * same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
