import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TYPES } from 'src/app/data/constant/type';
import { Artist } from 'src/app/data/model/artist';
import { AliasService } from 'src/app/service/alias.service';
import { ArtistService } from 'src/app/service/artist.service';

@Component({
  selector: 'app-artist-add',
  templateUrl: './artist-add.component.html',
  styleUrls: ['./artist-add.component.css'],
  providers: [DatePipe]

})

export class ArtistAddComponent 
{
    constructor(
                private router: Router, 
                private datePipe: DatePipe,
                private aliasService: AliasService,
                private artistService: ArtistService ){};
    
    artistType = Object.values(TYPES).filter(value => typeof value === 'string');

    name:string  = "";
    biography:string  = "";
    type:string  = "";
    dob:Date  = new Date;
    alias:string=""

    onSubmit(): void 
    {
      let newArtist:Artist = {} as Artist            
      newArtist.name = this.name
      newArtist.biography = this.biography
      newArtist.type = this.type
      newArtist.dob = this.datePipe.transform(this.dob,'yyyy-MM-dd')

      console.log(newArtist)
      this.artistService.create(newArtist).subscribe(response=>{
        this.aliasService.add(response.data.id, this.alias).subscribe(response=>{
          this.router.navigate(['/artists']);
        })
      });
    }

}
