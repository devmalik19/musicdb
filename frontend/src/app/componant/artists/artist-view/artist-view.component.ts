import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Track } from 'src/app/data/model/track';
import { ArtistService } from 'src/app/service/artist.service';

@Component({
  selector: 'app-artist-view',
  templateUrl: './artist-view.component.html',
  styleUrls: ['./artist-view.component.css']
})

export class ArtistViewComponent implements AfterViewInit
{
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private artistService: ArtistService ){}
  
  ngAfterViewInit(): void 
  {
    this.activatedRoute.params.subscribe(params => {
        this.id = params['id'];
        this.artistService.getArtistById(this.id).subscribe(response=>{
          this.name = response.data.name
          this.biography = response.data.biography
          this.type = response.data.type
          this.dob = response.data.dob
        })

        this.artistService.getArtistTracks(this.id).subscribe(response=>{
          this.trackList = response.data
        })

    })
  }


   id:string = ""
   name:string  = ""
   biography:string  = ""
   type:string  = ""
   dob:string  = ""
   trackList:Track[] = []
   

}
