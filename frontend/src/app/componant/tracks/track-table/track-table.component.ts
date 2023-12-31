import { Component, OnInit, ViewChild } from '@angular/core';
import { Track } from '../../../data/model/track';
import { TrackService } from '../../../service/track.service';
import { Router } from '@angular/router';
import { MatTableDataSource} from "@angular/material/table";
import { MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-track-table',
  templateUrl: './track-table.component.html',
  styleUrls: ['./track-table.component.css']
})
export class TrackTableComponent implements OnInit 
{
  constructor(private trackService:TrackService, private router: Router ){}

	displayedColumns = ['title', 'artists', 'album', 'genre', 'length', 'release', 'language', 'action']
	dataSource = new MatTableDataSource<Track>()

	ngOnInit()
	{
		this.trackService.getTrackList().subscribe(response=>{
			this.dataSource.data = response.data
			this.dataSource.paginator = this.paginator
		})
	}

	applyFilter(event: Event) 
	{
		const serach = (event.target as HTMLInputElement).value
		this.trackService.getTrackList(serach).subscribe(response=>{
			this.dataSource.data = response.data
		});
	}

	onRowClicked(id:string) 
	{
		console.log('Row clicked: ', id)
		this.router.navigate(['/tracks/edit', id])
	}
	
	add()
	{
		this.router.navigate(['/tracks/add'])
	}

	@ViewChild('paginator') paginator: MatPaginator | any
}
