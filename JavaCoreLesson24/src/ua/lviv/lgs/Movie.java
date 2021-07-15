package ua.lviv.lgs;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Movie implements Comparable<Movie> {

	private String title;
	private Time duration;
	public static TreeSet<Movie> movieSet = new TreeSet<Movie>();

	public Movie() throws TimeException {
		super();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a title for the movie ");
		String title = sc.next();
		System.out.println("Enter the duration of the movie (hours, 'Enter', minutes): ");
		Time duration = new Time(sc.nextByte(), sc.nextByte());
	
		this.title = title;
		this.duration = duration;
		movieSet.add(this);
	}

	public Movie(String title, Time duration) throws TimeException {
		super();
		this.title = title;
		this.duration = duration;
		movieSet.add(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Time getDuration() {
		return duration;
	}

	public static Set<Movie> getMovieSet() {
		return movieSet;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Film " + title + " -- " + duration.toString();
	}

	@Override
	public int compareTo(Movie o) {
		return this.title.compareTo(o.title);
	}

	public static Movie chooseMovie() {
		System.out.println();

		Iterator<Movie> iterator = movieSet.iterator();
		int i = 1;
		while (iterator.hasNext()) {
			Movie next = iterator.next();
			System.out.println(Integer.toString(i) + " - " + next.title);
			i++;
		}
		Scanner sc = new Scanner(System.in);
		int number = 0;
		if (sc.hasNextInt())
			number = sc.nextInt();
		if (number > i)
			return null;
		Movie[] movieArray;
		movieArray = movieSet.toArray(new Movie[movieSet.size()]);
		System.out.println("Selected " + movieArray[number - 1].toString());
		return movieArray[number - 1];
	}

}