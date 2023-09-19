import { Component, OnInit } from '@angular/core';
import { Track } from './data/track';
import {MatTableDataSource} from "@angular/material/table";
import { TrackService } from './service/track.service';


@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit
{
	constructor(private trackService:TrackService){};

	title = 'music-db';
	displayedColumns = ['title', 'duration'];
	track: Track[] = [];
	dataSource = new MatTableDataSource<Track>();

	ngOnInit()
	{
		this.trackService.getTrackList().subscribe(response=>{
			console.log(response.data);
			this.dataSource.data = response.data; 
		});
	}
}
