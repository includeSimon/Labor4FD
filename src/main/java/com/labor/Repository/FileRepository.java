package com.labor.Repository;

import com.labor.Exceptions.NullException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileRepository<T> implements ICrudRepository<T> {
    protected List<T> elemList;
    protected File fileIn;    //input data file
    protected File fileOut;   //output data file, will be created for every repository if it doesn't exist

    /**
     * reads and saves data to repository
     * @param fileIn name of the input file
     * @throws IOException error while reading from file
     */
    public FileRepository(String fileIn) throws IOException {

        this.fileIn = new File(fileIn);
    }

    /**
     * reads data from file
     * @return
     * @throws IOException
     */
    public abstract void readFromFile() throws IOException, NullException;

    /**
     * writes objects to file
     * @param object object of type Course, Teacher or Student to be written to output file
     * @throws IOException error while reading from file
     */
    public abstract void writeToFile(List<T> object) throws IOException;


    @Override
    public abstract List<T> findAll();

}
