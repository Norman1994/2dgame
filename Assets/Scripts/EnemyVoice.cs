using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyVoice : MonoBehaviour {

	private ObstracleEnemy enemy;

	// Use this for initialization
	void Start () 
	{
        enemy = Resources.Load<ObstracleEnemy> ("ObstracleEnemy");
        //enemy = gameObject.GetComponent<ObstracleEnemy>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void OnTriggerEnter2D(Collider2D collider)
	{
		if (collider.tag == "Character") 
		{
			//enemy.Attack();
			Destroy (gameObject);
		}
	}
}
