import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map} from 'rxjs/operators';
import { ApiResponse } from '../data/dto/api.response';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AliasService 
{
  constructor(private http: HttpClient) {}

  getAliases(artistId:string) 
  {
	  let apiResponse: ApiResponse = {} as ApiResponse;
	  return this.http.get<ApiResponse>(environment.API_URL + '/alias/' + artistId, { observe: 'response' }).pipe(map( response => { 
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

  add(artistId:string, name:string) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.patch<ApiResponse>(environment.API_URL + '/alias/' + artistId + '/add?name=' + name, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			return apiResponse;
		}));
	}


  remove(aliasId:string) 
	{
		let apiResponse: ApiResponse = {} as ApiResponse;
		return this.http.patch<ApiResponse>(environment.API_URL + '/alias/remove/' + aliasId, { observe: 'response' }).pipe(map( response => { 
			console.log(response)
			return apiResponse;
		}));
	}
}
