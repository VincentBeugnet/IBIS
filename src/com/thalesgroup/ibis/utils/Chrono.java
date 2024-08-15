package com.thalesgroup.ibis.utils;

public class Chrono {

	private double start = 0;
	private double end = 0;


	public void start()
	{
		start = System.nanoTime() / 1000000000f;
	}

	public void resume()
	{
		start = start - end + System.nanoTime() / 1000000000f;
	}

	public void stop()
	{
		if (start == 0) {return;}
		end = System.nanoTime() / 1000000000f;
	}

	public double getTime()
	{
		return (end - start);
	}
}
