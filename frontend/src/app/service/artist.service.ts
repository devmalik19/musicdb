import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { ApiResponse } from '../data/dto/api.response';
import { environment } from '../../environments/environment';
import { Artist } from '../data/model/artist';

@Injectable({
  providedIn: 'root'
})
export class ArtistService 
{
  constructor(private http: HttpClient) {}

	getArtistTracks(id:string) : Observable<ApiResponse>
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.get<ApiResponse>(environment.API_URL + '/artist/' + id + '/tracks', { observe: 'response' }).pipe(map( response => { 
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

  	getArtistOfTheDay() : Observable<ApiResponse>
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.get<ApiResponse>(environment.API_URL + '/artist/aotd', { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			apiResponse.status = response.status
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

	getArtistList(search = '', pageNumber = 0, pageSize = 3) : Observable<ApiResponse>
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.get<ApiResponse>(environment.API_URL + '/artist?search='+search, { observe: 'response' }).pipe(map( response => { 
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

	getArtistById(id:string) : Observable<ApiResponse>
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.get<ApiResponse>(environment.API_URL + '/artist/'+id, { observe: 'response' }).pipe(map( response => { 
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

	create(artist:Artist) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.post<ApiResponse>(environment.API_URL + '/artist', artist, { observe: 'response' }).pipe(map( response => { 
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

	update(artist:Artist) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.patch<ApiResponse>(environment.API_URL + '/artist/'+artist.id, artist, { observe: 'response' }).pipe(map( response => { 
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
}
