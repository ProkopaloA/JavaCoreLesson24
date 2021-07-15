package ua.lviv.lgs;

public class Seance implements Comparable<Seance> {
	private Movie movie;
	private Time startTime;
	private Time endTime;

	public Time calculateEndTime(Time startTime, Time duration) throws TimeException {
	
		int hour = startTime.getHour() + duration.getHour();
		int min = startTime.getMin() + duration.getMin();
		while (min >= 60) {
			hour++;
			min -= 60;
		}
		return new Time(hour,min) ;
	}

	public Seance(Movie movie, Time startTime) throws TimeException {
		super();
		int hour = 0;
		int min = 0;
		hour = startTime.getHour() + movie.getDuration().getHour();
		min = startTime.getMin() + movie.getDuration().getMin();
		while (min >= 60) {
			hour++;
			min -= 60;
		}
		Time endTime = new Time(hour , min , false);

		validTime(startTime, endTime, Cinema.getTimeOpen(), Cinema.getTimeClose());
		this.movie = movie;
		this.startTime = startTime;
		this.endTime = calculateEndTime(startTime, movie.getDuration());
	}

	
	
	private static boolean validTime(Time startTime, Time endTime, Time timeOpen, Time timeClose) throws TimeException  {
		
		if (endTime.getHour() >= timeClose.getHour() ) 
		
			throw new TimeException("Outside hours! Time Close "+ timeClose.getHour()+":"+timeClose.getMin());
		
		if (startTime.getHour() <= timeOpen.getHour()) {
			throw new TimeException("Outside minutes");
		}
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "Session \"" + movie + "\", beginning at " + startTime.toString() + ", end  at " + endTime.toString();
	}

	public Movie getMovie() {
		return movie;
	} 

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	@Override
	public int compareTo(Seance o) {
		if (this.endTime.getHour() > o.endTime.getHour())
			return 1;
		if (this.endTime.getHour() == o.endTime.getHour() && this.endTime.getMin() > o.endTime.getMin())
			return 1;
		return -1;
	}

}