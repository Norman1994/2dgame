using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharBullet : MonoBehaviour 
{

	private GameObject parent;
	public GameObject Parent {set { parent = value;} get { return parent;}}

	private float speed = 10.0f;
	private Vector3 direction;
	public Vector3 Direction {set { direction = value;}}

	private SpriteRenderer sprite;
	private Character ch;

	public static bool damage;

	private void Awake()
	{
		sprite = GetComponentInChildren<SpriteRenderer> ();
	}

	private void Start()
	{
		Destroy (gameObject, 1.4f);
	}

	private void Update()
	{
		transform.position = Vector3.MoveTowards(transform.position, transform.position + direction, speed * Time.deltaTime);
		damage = false;
	}

	private void OnTriggerEnter2D(Collider2D collider)
	{
		Unit unit = collider.GetComponent<Unit> ();

		if (unit && unit.gameObject != parent) 
		{
			damage = true;
			Destroy(gameObject);
		}
	}
}
