function validate(frm) {
	// empty the form validation error messages
	document.getElementById("mNameErr").innerHTML = "";
	document.getElementById("mCastErr").innerHTML = "";
	document.getElementById("mCategoryErr").innerHTML = "";
	document.getElementById("mPosterErr").innerHTML = "";
	document.getElementById("movieErr").innerHTML = "";
	//read from data
	let movieName = frm.movieName.value;
	let cast = frm.movieCast.value;
	let category = frm.category.value;
	let poster = frm.moviePoster.value;
	let movie = frm.movie.value;
	//form validation logic  (client side)
	if (movieName == "") {
		document.getElementById("mNameErr").innerHTML = "Movie name is required!";
		flag = false;
	}

	if (cast == "") {
		document.getElementById("mCastErr").innerHTML = "Movie cast is required!";
		flag = false;
	}
	if (category == "") {
		document.getElementById("mCastErr").innerHTML = "Movie category is required!";
		flag = false;
	}
	/*	if (poster.files.length == 0) {
			document.getElementById("mCastErr").innerHTML = "Movie poster is required";
			flag = false;
		}
		if (movie.files.length == 0) {
			document.getElementById("mCastErr").innerHTML = "Movie file is required";
			flag = false;
		}*/

}