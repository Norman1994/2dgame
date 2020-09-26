using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeathZone : MonoBehaviour {

	private void OnTriggerEnter2D(Collider2D collider)
	{
		Character character = collider.GetComponent<Character> ();
		if (character) 
		{
			character.Lives = 0;
			Application.LoadLevel ("DeathScene");
		}
	}
}
