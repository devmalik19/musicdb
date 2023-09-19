import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { ApiResponse } from '../data/api.response';
import { environment } from '../../environments/environment';

@Injectable({
	providedIn: 'root',
})
export class TrackService
{
	constructor(private http: HttpClient) {}

	getTrackList(search = '', pageNumber = 0, pageSize = 3) : Observable<ApiResponse>
	{
		return this.http.get<ApiResponse>(environment.API_URL + '/track').pipe(map( response => { 
			return response;
		}));
	}
}
