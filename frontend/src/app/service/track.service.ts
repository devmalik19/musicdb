import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { ApiResponse } from '../data/dto/api.response';
import { environment } from '../../environments/environment';
import { Track } from '../data/model/track';

@Injectable({
	providedIn: 'root',
})
export class TrackService
{

	constructor(private http: HttpClient) {}

	getTrackList(search = '', pageNumber = 0, pageSize = 3) : Observable<ApiResponse>
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.get<ApiResponse>(environment.API_URL + '/track?search='+search, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			switch(response.status)
			{
				case 200:
					apiResponse.data = response.body?.data
				break;
				case 204:
					apiResponse.data = ""
				break;
			}
			return apiResponse;
		}));
	}

	getTrackById(id:string) : Observable<ApiResponse>
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.get<ApiResponse>(environment.API_URL + '/track/'+id, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			switch(response.status)
			{
				case 200:
					apiResponse.data = response.body?.data
				break;
				case 204:
					apiResponse.data = ""
				break;
			}
			return apiResponse;
		}));
	}

	create(track:Track) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.post<ApiResponse>(environment.API_URL + '/track', track, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			return apiResponse;
		}));
	}

	update(track:Track) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.patch<ApiResponse>(environment.API_URL + '/track/'+track.id, track, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			return apiResponse;
		}));
	}
}
