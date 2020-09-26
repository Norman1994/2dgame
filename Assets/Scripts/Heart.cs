using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Heart : MonoBehaviour 
{
	public static bool check = false;
	private void OnTriggerEnter2D(Collider2D collider)
	{
		Character character = collider.GetComponent<Character> ();
		if (character) 
		{
			check = true;
			character.Lives++;
			Destroy (gameObject);
		}
	}
}
