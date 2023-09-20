import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GENRE } from 'src/app/data/constant/genre';
import { Track } from 'src/app/data/model/track';
import { TrackService } from 'src/app/service/track.service';


@Component({
  selector: 'app-track-edit',
  templateUrl: './track-edit.component.html',
  styleUrls: ['./track-edit.component.css']
})

export class TrackEditComponent implements OnInit 
{
    constructor(private activatedRoute: ActivatedRoute,
                private router: Router, 
                private trackService: TrackService ){};
    
    genreList = Object.values(GENRE).filter(value => typeof value === 'string');

    track:Track = {} as Track            
    id:string = "";
    title:string = "";
    album:string = "";
    genre:string = "";
    length:string = "";
    release:string  = "";
    language:string  = "";          

    ngOnInit()
    {
      this.activatedRoute.params.subscribe(params => {
          this.id = params['id'];
          this.trackService.getTrackById(this.id).subscribe(response=>{
            this.title = response.data.title
            this.album = response.data.album
            this.genre = response.data.genre
            this.length = response.data.length
            this.release = response.data.release
            this.language = response.data.language
          });
      });
    }

    onSubmit(): void 
    {
      this.track.id = this.id
      this.track.title = this.title
      this.track.album = this.album
      this.track.genre = this.genre
      this.track.length = this.length
      this.track.release = this.release
      this.track.language = this.language

      this.trackService.update(this.track).subscribe(response=>{
        this.router.navigate(['/tracks']);
      });
    }
}
