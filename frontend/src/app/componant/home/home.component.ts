import { Component } from '@angular/core';
import { ArtistService } from '../../service/artist.service';
import { Router } from '@angular/router';
import { Artist } from '../../data/model/artist';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent 
{

  constructor(private artistService:ArtistService, private router: Router ){};

	artistOftheDay: Artist = {} as Artist

	ngOnInit()
	{
		this.artistService.getArtistOfTheDay().subscribe(response=>{
			console.log(response);
			this.artistOftheDay = response.data; 
		});
	}

}
