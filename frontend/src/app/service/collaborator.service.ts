import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map} from 'rxjs/operators';
import { ApiResponse } from '../data/dto/api.response';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class CollaboratorService 
{
  constructor(private http: HttpClient) {}

  add(trackId:string, artistId:string, role="SINGER") 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.patch<ApiResponse>(environment.API_URL + '/collab/' + trackId + '/add/' + artistId + '?role=' + role, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			return apiResponse;
		}));
	}


  remove(trackId:string, artistId:string) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.patch<ApiResponse>(environment.API_URL + '/collab/' + trackId + '/remove/' + artistId, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			return apiResponse;
		}));
	}

}
