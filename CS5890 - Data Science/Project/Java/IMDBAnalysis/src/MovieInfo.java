public class MovieInfo {
	
	private String title;
	
	private double usGrossNormz;
	private double worldwideGrossNormz;
	private double usDvdSalesNormz;
	
	private double rottenTomatoRatingNormz;
	private double imdbRatingNormz;
	private double imdbVotesNormz;
	
	private double mppaRatingNormz;
	private double runningTimeNormz;
	private double distributorNormz;
	private double sourceNormz;
	private double majorGenreNormz;
	
	/*private double usGross;
	private double worldwideGross;
	private double usDvdSales;
	private double productionBudget;
	private double productionBudgetNormz;
	private String releaseDate;
	private String mppaRating;
	private double runningTime;
	private String distributor;
	private String source;
	private String majorGenre;
	private String creativeType;
	private String director;
	private double rottenTomatoRating;
	private double imdbRating;
	private double imdbVotes;*/
	
	
	public MovieInfo(String title, double usGrossNormz, double worldwideGrossNormz, double usDvdSalesNormz,
			double rottenTomatoRatingNormz, double imdbRatingNormz, double imdbVotesNormz, double mppaRatingNormz,
			double runningTimeNormz, double distributorNormz, double sourceNormz, double majorGenreNormz) {
		super();
		this.title = title;
		this.usGrossNormz = usGrossNormz;
		this.worldwideGrossNormz = worldwideGrossNormz;
		this.usDvdSalesNormz = usDvdSalesNormz;
		this.rottenTomatoRatingNormz = rottenTomatoRatingNormz;
		this.imdbRatingNormz = imdbRatingNormz;
		this.imdbVotesNormz = imdbVotesNormz;
		this.mppaRatingNormz = mppaRatingNormz;
		this.runningTimeNormz = runningTimeNormz;
		this.distributorNormz = distributorNormz;
		this.sourceNormz = sourceNormz;
		this.majorGenreNormz = majorGenreNormz;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getUsGrossNormz() {
		return usGrossNormz;
	}

	public void setUsGrossNormz(double usGrossNormz) {
		this.usGrossNormz = usGrossNormz;
	}

	public double getWorldwideGrossNormz() {
		return worldwideGrossNormz;
	}

	public void setWorldwideGrossNormz(double worldwideGrossNormz) {
		this.worldwideGrossNormz = worldwideGrossNormz;
	}

	public double getUsDvdSalesNormz() {
		return usDvdSalesNormz;
	}

	public void setUsDvdSalesNormz(double usDvdSalesNormz) {
		this.usDvdSalesNormz = usDvdSalesNormz;
	}

	public double getRottenTomatoRatingNormz() {
		return rottenTomatoRatingNormz;
	}

	public void setRottenTomatoRatingNormz(double rottenTomatoRatingNormz) {
		this.rottenTomatoRatingNormz = rottenTomatoRatingNormz;
	}

	public double getImdbRatingNormz() {
		return imdbRatingNormz;
	}

	public void setImdbRatingNormz(double imdbRatingNormz) {
		this.imdbRatingNormz = imdbRatingNormz;
	}

	public double getImdbVotesNormz() {
		return imdbVotesNormz;
	}

	public void setImdbVotesNormz(double imdbVotesNormz) {
		this.imdbVotesNormz = imdbVotesNormz;
	}

	public double getMppaRatingNormz() {
		return mppaRatingNormz;
	}

	public void setMppaRatingNormz(double mppaRatingNormz) {
		this.mppaRatingNormz = mppaRatingNormz;
	}

	public double getRunningTimeNormz() {
		return runningTimeNormz;
	}

	public void setRunningTimeNormz(double runningTimeNormz) {
		this.runningTimeNormz = runningTimeNormz;
	}

	public double getDistributorNormz() {
		return distributorNormz;
	}

	public void setDistributorNormz(double distributorNormz) {
		this.distributorNormz = distributorNormz;
	}

	public double getSourceNormz() {
		return sourceNormz;
	}

	public void setSourceNormz(double sourceNormz) {
		this.sourceNormz = sourceNormz;
	}

	public double getMajorGenreNormz() {
		return majorGenreNormz;
	}

	public void setMajorGenreNormz(double majorGenreNormz) {
		this.majorGenreNormz = majorGenreNormz;
	}
	
	
	
	
	
}
