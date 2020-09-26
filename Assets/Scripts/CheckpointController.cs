using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CheckpointController : MonoBehaviour {

	public bool checkpointReached;

	void OnTriggerEnter2D(Collider2D collider)
	{
		Character character = collider.GetComponent<Character> ();
		if (character) 
		{
			checkpointReached = true;
		
		}
	}
}
